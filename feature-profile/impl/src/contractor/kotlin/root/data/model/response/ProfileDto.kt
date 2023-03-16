package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileDto(
    @SerialName("user")
    val user: ProfileUserDto,
    @SerialName("contractor")
    val info: ProfileInfoDto
)
