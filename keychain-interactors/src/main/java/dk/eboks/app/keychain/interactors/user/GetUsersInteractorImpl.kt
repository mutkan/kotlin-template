package dk.eboks.app.keychain.interactors.user

import dk.eboks.app.domain.managers.UserManager
import dk.eboks.app.util.exceptionToViewError
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor
import javax.inject.Inject

/**
 * Created by bison on 24-06-2017.
 */
internal class GetUsersInteractorImpl @Inject constructor(
    executor: Executor,
    private val userManager: UserManager
) :
    BaseInteractor(executor), GetUsersInteractor {
    override var output: GetUsersInteractor.Output? = null

    override fun execute() {
        try {
            runOnUIThread {
                output?.onGetUsers(userManager.users)
            }
        } catch (t: Throwable) {
            runOnUIThread {
                output?.onGetUsersError(exceptionToViewError(t))
            }
        }
    }
}