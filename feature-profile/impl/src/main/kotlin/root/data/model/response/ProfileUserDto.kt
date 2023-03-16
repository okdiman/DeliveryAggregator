package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileUserDto(
    @SerialName("phone")
    val phone: String,
    @SerialName("role")
    val role: ProfileRoleDto,
    @SerialName("settings")
    val settings: ProfileSettingsDto?
)
