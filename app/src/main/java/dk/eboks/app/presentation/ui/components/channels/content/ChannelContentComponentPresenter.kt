package dk.eboks.app.presentation.ui.components.channels.content

import dk.eboks.app.domain.interactors.channel.GetChannelContentLinkInteractor
import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.models.channel.Channel
import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.domain.models.shared.Link
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */

class ChannelContentComponentPresenter @Inject constructor(val appState: AppStateManager, val getChannelContentLinkInteractor: GetChannelContentLinkInteractor) :
        ChannelContentComponentContract.Presenter,
        BasePresenterImpl<ChannelContentComponentContract.View>(),
        GetChannelContentLinkInteractor.Output
{

    init {
        getChannelContentLinkInteractor.output = this
    }


    override fun setup(channel : Channel) {
        runAction { v->v.showChannel(channel) }
        getChannelContentLinkInteractor.input = GetChannelContentLinkInteractor.Input(channelId = channel.id)
        getChannelContentLinkInteractor.run()
    }

    override fun onGetChannelContentLink(link: Link) {
        runAction { v->v.openChannelLink(link) }
    }

    override fun onGetChannelContentLinkError(error: ViewError) {
        runAction { v->v.showErrorDialog(error) }
    }
}