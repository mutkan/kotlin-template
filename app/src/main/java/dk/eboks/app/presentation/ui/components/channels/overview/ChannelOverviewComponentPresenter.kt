package dk.eboks.app.presentation.ui.components.channels.overview

import dk.eboks.app.domain.interactors.channel.GetChannelsInteractor
import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.models.channel.Channel
import dk.eboks.app.domain.models.local.ViewError
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
class ChannelOverviewComponentPresenter @Inject constructor(val appState: AppStateManager, val getChannelsInteractor: GetChannelsInteractor) :
        ChannelOverviewComponentContract.Presenter,
        BasePresenterImpl<ChannelOverviewComponentContract.View>(),
        GetChannelsInteractor.Output
{

    init {
        getChannelsInteractor.output = this
    }

    override fun setup() {
        //refresh(true)
    }

    override fun refresh(cached : Boolean)
    {
        getChannelsInteractor.input = GetChannelsInteractor.Input(cached)
        getChannelsInteractor.run()
    }

    override fun openChannel(channel: Channel) {
        /*
        appState.state?.channelState?.let { state ->
            state.selectedChannel = channel
        }
        */
        runAction { v-> v.showChannelOpening(channel) }
    }

    override fun onGetChannels(channels: List<Channel>) {
        runAction { v->
            v.showChannels(channels)
            v.showProgress(false)
        }
    }

    override fun onGetChannelsError(error : ViewError) {
        runAction { it.showErrorDialog(error) }
    }
}