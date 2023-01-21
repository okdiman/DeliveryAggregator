package root.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthSendVerifyCodeRequest(
    val phone: String
)