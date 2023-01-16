package data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthAddressSuggestRequest(
    val query: String,
    val code: Int,
    val phone: String
)