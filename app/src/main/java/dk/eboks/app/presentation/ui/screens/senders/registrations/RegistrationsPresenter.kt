package dk.eboks.app.presentation.ui.screens.senders.registrations

import dk.eboks.app.domain.interactors.sender.register.GetRegistrationsInteractor
import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.domain.models.sender.Registrations
import dk.nodes.arch.presentation.base.BasePresenterImpl

/**
 * Created by Christian on 3/28/2018.
 * @author   Christian
 * @since    3/28/2018.
 */
class RegistrationsPresenter(val appStateManager: AppStateManager, registrationsInteractor: GetRegistrationsInteractor) :
        BasePresenterImpl<RegistrationsContract.View>(),
        RegistrationsContract.Presenter,
        GetRegistrationsInteractor.Output {

    init {
        registrationsInteractor.output = this
        registrationsInteractor.run()
    }

    override fun onRegistrationsLoaded(registrations: Registrations) {
        runAction { v ->
            v.showRegistrations(registrations)
        }
    }

    override fun onError(error: ViewError)  {
        runAction { v ->
            v.showErrorDialog(error)
        }
    }
}