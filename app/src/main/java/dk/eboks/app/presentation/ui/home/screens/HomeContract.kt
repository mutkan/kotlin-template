package dk.eboks.app.presentation.ui.home.screens

import dk.eboks.app.domain.models.folder.Folder
import dk.eboks.app.presentation.base.BaseView
import dk.nodes.arch.presentation.base.BasePresenter

/**
 * Created by bison on 07-11-2017.
 */
interface HomeContract {
    interface View : BaseView {
        fun addFolderPreviewComponentFragment(folder : Folder)
        fun addChannelControlComponentFragment()
        fun showMailsHeader(show : Boolean)
        fun showChannelControlsHeader(show : Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun setup()
    }
}