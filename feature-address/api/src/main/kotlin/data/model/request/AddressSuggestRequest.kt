package data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddressSuggestRequest(
    val query: String,
    val code: Int,
    val phone: String
)