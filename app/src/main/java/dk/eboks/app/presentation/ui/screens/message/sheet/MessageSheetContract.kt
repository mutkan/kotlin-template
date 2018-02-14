package dk.eboks.app.presentation.ui.screens.message.sheet

import dk.nodes.arch.presentation.base.BasePresenter
import dk.nodes.arch.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface MessageSheetContract {
    interface View : BaseView {
        fun addHeaderComponentFragment()
        fun addNotesComponentFragment()
        fun addAttachmentsComponentFragment()
        fun addFolderInfoComponentFragment()
        fun showError(msg : String)
    }

    interface Presenter : BasePresenter<MessageSheetContract.View> {
    }
}