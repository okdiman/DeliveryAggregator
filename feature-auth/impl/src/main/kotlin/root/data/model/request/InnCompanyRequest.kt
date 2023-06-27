package root.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class InnCompanyRequest(
    val code: Int,
    val phone: String,
    val query: String
)