package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthSuccessDto(
    @SerialName("token")
    val token: String,
    @SerialName("tokenInfo")
    val tokenInfo: AuthTokenInfoDto?
)
