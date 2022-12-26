package data.model.response

import com.google.gson.annotations.SerializedName

class ProfileUserDto(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("role")
    val role: ProfileRoleDto,
    @SerializedName("settings")
    val settings: ProfileSettingsDto
)