package dk.eboks.app.presentation.ui.components.uploads.uploadfile

import dk.eboks.app.presentation.base.BaseView
import dk.nodes.arch.presentation.base.BasePresenter

/**
 * Created by bison on 07-11-2017.
 */
interface UploadFileComponentContract {
    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {
    }
}