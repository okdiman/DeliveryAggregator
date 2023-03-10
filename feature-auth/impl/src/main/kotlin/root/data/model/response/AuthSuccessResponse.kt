package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthSuccessResponse(
    @SerialName("token")
    val token: String
)
