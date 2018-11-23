package dk.eboks.app.domain.interactors.message

import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.domain.models.message.Message
import dk.nodes.arch.domain.interactor.Interactor

/**
 * Created by bison on 01/02/18.
 */
interface DeleteMessagesInteractor : Interactor {
    var output: Output?
    var input: Input?

    data class Input(val message :  Message)

    interface Output {
        fun onDeleteMessagesSuccess()
        fun onDeleteMessagesError(error : ViewError)
    }
}