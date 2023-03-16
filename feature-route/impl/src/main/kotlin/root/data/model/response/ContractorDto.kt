package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContractorDto(
    val userId: Long,
    val name: String,
    val secondName: String,
    val surname: String,
    val email: String,
    val inn: String,
    val kpp: String,
    val ogrn: String,
    val bik: String,
    val legalAddress: String,
    val actualAddress: String,
    val checkingAccount: String,
    val correspondentAccount: String,
    val bank: String,
    @SerialName("organisationName")
    val organizationName: String,
    val carPlate: String,
    val carModel: String?,
    val carCategory: String?,
    val carLoadCapacity: Double?,
    val carPalletCapacity: Int?,
    val phone: String,
)
