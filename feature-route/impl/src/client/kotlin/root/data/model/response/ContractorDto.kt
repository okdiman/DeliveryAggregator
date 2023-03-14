package root.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ContractorDto(
    val phone: String,
    val email: String,
    val surname: String,
    val name: String,
    val secondName: String,
    val carPlate: String,
    val carModel: String,
)
