package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileRoleDto(
    @SerialName("title")
    val title: String
)
