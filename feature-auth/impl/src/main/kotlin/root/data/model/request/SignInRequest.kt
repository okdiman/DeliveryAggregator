package root.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val code: Int,
    val phone: String
)