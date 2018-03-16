package dk.eboks.app.domain.models.login

import dk.eboks.app.domain.config.LoginProvider
import java.io.Serializable

data class User(
    var id : Long = -1,
    var name : String,
    var email : String? = null,
    var cpr : String? = null,
    var avatarUri : String? = null,
    var lastLoginProvider: LoginProvider? = null,
    var verified : Boolean,
    var hasFingerprint : Boolean
) : Serializable