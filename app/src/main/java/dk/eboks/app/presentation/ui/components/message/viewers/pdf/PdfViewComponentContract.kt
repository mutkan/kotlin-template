package dk.eboks.app.presentation.ui.components.message.viewers.pdf

import dk.eboks.app.domain.models.Folder
import dk.nodes.arch.presentation.base.BasePresenter
import dk.nodes.arch.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface PdfViewComponentContract {
    interface View : BaseView {
        fun updateView(folder : Folder)
    }

    interface Presenter : BasePresenter<View> {
    }
}