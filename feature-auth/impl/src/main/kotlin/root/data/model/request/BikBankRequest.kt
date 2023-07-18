package root.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class BikBankRequest(
    val bik: String,
    val code: Int,
    val phone: String,
)