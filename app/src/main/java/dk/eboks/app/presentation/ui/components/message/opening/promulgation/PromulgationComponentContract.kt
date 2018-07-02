package dk.eboks.app.presentation.ui.components.message.opening.promulgation

import dk.eboks.app.presentation.base.BaseView
import dk.nodes.arch.presentation.base.BasePresenter

/**
 * Created by bison on 07-11-2017.
 */
interface PromulgationComponentContract {
    interface View : BaseView {
        fun setPromulgationText(promulgationText: String)
    }

    interface Presenter : BasePresenter<View> {
        fun setShouldProceed(proceed : Boolean)
    }
}