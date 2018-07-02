package dk.eboks.app.domain.interactors.authentication

import dk.eboks.app.domain.exceptions.InteractorException
import dk.eboks.app.domain.managers.*
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.network.Api
import dk.eboks.app.util.exceptionToViewError
import dk.eboks.app.util.guard
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor
import timber.log.Timber


/**
 * Created by Christian on 5/28/2018.
 * @author   Christian
 * @since    5/28/2018.
 */
class MergeAndImpersonateInteractorImpl(
        executor: Executor, val api: Api,
        val appStateManager: AppStateManager,
        val userManager: UserManager,
        val userSettingsManager: UserSettingsManager,
        val authClient: AuthClient,
        val cacheManager: CacheManager
) : BaseInteractor(executor), MergeAndImpersonateInteractor {
    override var output: MergeAndImpersonateInteractor.Output? = null
    override var input: MergeAndImpersonateInteractor.Input? = null

    override fun execute() {
        try {
            input?.verificationState?.let { verificationState ->
                var targetUserId : String = ""
                // if user choose to merge profiles, do that first, otherwise impersonate only
                if(verificationState.shouldMergeProfiles)
                {
                    verificationState.allowMigrateUserId?.let { userId ->
                        targetUserId = userId
                        val result = api.migrateUser(userId ).execute()
                        if(!result.isSuccessful)
                        {
                            output?.onMergeError(ViewError(title = Translation.error.genericTitle, message = Translation.error.genericMessage, shouldCloseView = true)) // TODO better error
                            return
                        }

                        // after migrate call impersonate
                        appStateManager.state?.loginState?.token?.let {
                            authClient.impersonate(it.access_token, targetUserId)
                        }
                    }
                }

                appStateManager.state?.loginState?.token?.let { token->
                    val userResult = api.getUserProfile().execute()
                    userResult?.body()?.let { user->
                        // update the states
                        Timber.e("Saving user $user")
                        val newUser = userManager.put(user)
                        val newSettings = userSettingsManager.get(newUser.id)

                        appStateManager.state?.loginState?.userLoginProviderId?.let {
                            newSettings.lastLoginProviderId = it
                        }
                        appStateManager.state?.loginState?.activationCode?.let {
                            newSettings.activationCode = it
                        }

                        /*
                        appStateManager.state?.loginState?.lastUser?.let { lastUser ->
                            if (lastUser.id != newUser.id) {
                                Timber.e("Different user id detected on login, clearing caches")
                                cacheManager.clearStores()
                            }
                        }
                        */
                        cacheManager.clearStores()

                        userSettingsManager.put(newSettings)
                        appStateManager.state?.loginState?.lastUser = newUser
                        appStateManager.state?.currentUser = newUser
                        appStateManager.state?.currentSettings = newSettings
                        appStateManager.state?.verificationState = null
                    }
                }.guard { throw(InteractorException("no token found to impersonate")) }

                appStateManager.save()
                runOnUIThread {
                    output?.onMergeCompleted()
                }
            }.guard {
                runOnUIThread {
                    output?.onMergeError(ViewError(title = Translation.error.genericTitle, message = Translation.error.genericMessage, shouldCloseView = true)) // TODO better error
                }
            }
        } catch (t: Throwable) {
            runOnUIThread {
                output?.onMergeError(exceptionToViewError(t))
            }
        }
    }

}