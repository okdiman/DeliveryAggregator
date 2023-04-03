package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileSettingsDto(
    @SerialName("push")
    val push: Boolean,
    @SerialName("sms")
    val sms: Boolean
)
