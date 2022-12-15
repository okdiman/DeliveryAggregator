package data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendVerifyCodeRequest(
    val phone: String
)