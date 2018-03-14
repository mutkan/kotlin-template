package dk.eboks.app.presentation.ui.components.channels.content

import dk.eboks.app.domain.models.channel.Channel
import dk.nodes.arch.presentation.base.BasePresenter
import dk.nodes.arch.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface ChannelContentComponentContract {
    interface View : BaseView {
        fun showChannel(channel : Channel)
    }

    interface Presenter : BasePresenter<View> {
    }
}