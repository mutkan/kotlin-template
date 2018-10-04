package dk.eboks.app.domain.interactors.folder

import dk.eboks.app.domain.repositories.FoldersRepository
import dk.eboks.app.util.exceptionToViewError
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor

class CreateFolderInteractorImpl(
        executor: Executor,
        val foldersRepository: FoldersRepository)
    :
        BaseInteractor(executor),
        CreateFolderInteractor {
    override var output: CreateFolderInteractor.Output? = null
    override var input: CreateFolderInteractor.Input? = null

    override fun execute() {
        try {
            input?.folderRequest?.let { folderRequest ->
                val folderRequesttest = folderRequest
                //todo find out what the userId is used for
                folderRequesttest.userId = null
                    foldersRepository.createFolder(folderRequesttest)
                    runOnUIThread {
                        output?.onCreateFolderSuccess()
                    }
            }
        } catch (e: Throwable) {
            runOnUIThread {
                output?.onCreateFolderError(exceptionToViewError(e))
            }
        }
    }
}