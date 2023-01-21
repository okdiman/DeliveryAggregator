package root.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthSignInRequest(
    val code: Int,
    val phone: String
)