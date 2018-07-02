package dk.eboks.app.presentation.ui.components.profile.drawer

import dk.eboks.app.presentation.base.BaseView
import dk.nodes.arch.presentation.base.BasePresenter

/**
 * Created by bison on 07-11-2017.
 */
interface MergeAccountComponentContract {
    interface View : BaseView {
        fun close()
    }

    interface Presenter : BasePresenter<View> {
        fun setMergeStatus(shouldMerge : Boolean)
    }
}