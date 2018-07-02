package dk.eboks.app.presentation.ui.components.message.viewers.html

import dk.eboks.app.presentation.base.BaseView
import dk.nodes.arch.presentation.base.BasePresenter

/**
 * Created by bison on 07-11-2017.
 */
interface HtmlViewComponentContract {
    interface View : BaseView {
        fun showHtml(filename : String)
    }

    interface Presenter : BasePresenter<View> {
    }
}