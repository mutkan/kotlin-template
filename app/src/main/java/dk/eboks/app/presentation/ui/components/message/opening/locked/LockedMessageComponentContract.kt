package dk.eboks.app.presentation.ui.components.message.opening.locked

import dk.nodes.arch.presentation.base.BasePresenter
import dk.eboks.app.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface LockedMessageComponentContract {
    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {
    }
}