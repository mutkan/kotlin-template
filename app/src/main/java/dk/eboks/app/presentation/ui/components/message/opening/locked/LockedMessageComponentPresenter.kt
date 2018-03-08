package dk.eboks.app.presentation.ui.components.message.opening.locked

import dk.eboks.app.domain.managers.AppStateManager
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
class LockedMessageComponentPresenter @Inject constructor(val appState: AppStateManager) : LockedMessageComponentContract.Presenter, BasePresenterImpl<LockedMessageComponentContract.View>() {

    init {
    }

}