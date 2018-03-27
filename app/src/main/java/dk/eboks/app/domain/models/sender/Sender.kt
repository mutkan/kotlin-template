package dk.eboks.app.domain.models.sender

import dk.eboks.app.domain.models.Image
import dk.eboks.app.domain.models.message.Message
import dk.eboks.app.domain.models.shared.Address
import dk.eboks.app.domain.models.shared.Description
import dk.eboks.app.domain.models.shared.Status
import java.io.Serializable

/**
 * Created by bison on 24-06-2017.
 */
data class Sender(
        var id: Long,
        var name: String = "",
        var description: Description? = null,
        var address: Address? = null,
        var authority: Int = 0,
        var logo: Image? = null,
        var groups : List<SenderGroup>? = null,
        var registered: Int? = 0, // (0: No, 1: Yes, 2: Partial)
        var messages : List<Message>? = null,
        var status : Status? = null,
        var unreadCount: Int = 0 // todo this should be removed - its not in the draft
) : Serializable