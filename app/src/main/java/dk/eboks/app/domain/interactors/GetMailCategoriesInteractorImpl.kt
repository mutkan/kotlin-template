package dk.eboks.app.domain.interactors

import dk.eboks.app.domain.repositories.MailCategoriesRepository
import dk.eboks.app.util.exceptionToViewError
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor

/**
 * Created by bison on 01/02/18.
 */
class GetMailCategoriesInteractorImpl(executor: Executor, val foldersRepositoryMail: MailCategoriesRepository) : BaseInteractor(executor), GetCategoriesInteractor {
    override var output: GetCategoriesInteractor.Output? = null
    override var input: GetCategoriesInteractor.Input? = null

    override fun execute() {
        try {
            val senders = foldersRepositoryMail.getMailCategories(input?.cached ?: true)
            runOnUIThread {
                output?.onGetCategories(senders)
            }
        } catch (t: Throwable) {
            runOnUIThread {
                output?.onGetCategoriesError(exceptionToViewError(t))
            }
        }
    }
}