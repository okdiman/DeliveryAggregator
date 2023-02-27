package root.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddressAuthSuggestRequest(
    val query: String,
    val code: Int,
    val phone: String
)