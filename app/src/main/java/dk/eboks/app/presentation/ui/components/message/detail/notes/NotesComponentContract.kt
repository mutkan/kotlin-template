package dk.eboks.app.presentation.ui.components.message.detail.notes

import dk.eboks.app.domain.models.Message
import dk.nodes.arch.presentation.base.BasePresenter
import dk.nodes.arch.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface NotesComponentContract {
    interface View : BaseView {
        fun updateView(message : Message)
    }

    interface Presenter : BasePresenter<NotesComponentContract.View> {
    }
}