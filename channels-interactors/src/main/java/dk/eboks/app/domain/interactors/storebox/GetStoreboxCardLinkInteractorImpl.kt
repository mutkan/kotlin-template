package dk.eboks.app.domain.interactors.storebox

import dk.eboks.app.domain.interactors.storebox.GetStoreboxCardLinkInteractor.Companion.ERROR_CALLBACK
import dk.eboks.app.domain.interactors.storebox.GetStoreboxCardLinkInteractor.Companion.SUCCESS_CALLBACK
import dk.eboks.app.network.Api
import dk.eboks.app.util.exceptionToViewError
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor
import timber.log.Timber
import javax.inject.Inject

internal class GetStoreboxCardLinkInteractorImpl @Inject constructor(
    executor: Executor,
    private val api: Api
) : BaseInteractor(executor), GetStoreboxCardLinkInteractor {
    override var output: GetStoreboxCardLinkInteractor.Output? = null

    override fun execute() {
        try {
            val result = api.getStoreboxCardLink(SUCCESS_CALLBACK, ERROR_CALLBACK).execute()

            result.body()?.let {
                runOnUIThread {
                    output?.onGetStoreboxCardLink(it)
                }
            }
        } catch (t: Throwable) {
            Timber.e(t)
            runOnUIThread {
                output?.onGetStoreboxCardLinkError(exceptionToViewError(t))
            }
        }
    }
}