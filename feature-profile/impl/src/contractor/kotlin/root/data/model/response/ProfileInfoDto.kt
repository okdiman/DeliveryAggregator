package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Suppress("LongParameterList")
class ProfileInfoDto(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("surname")
    val surname: String,
    @SerialName("secondName")
    val secondName: String,
    @SerialName("inn")
    val inn: String,
    @SerialName("kpp")
    val kpp: String,
    @SerialName("ogrn")
    val ogrn: String,
    @SerialName("bik")
    val bik: String,
    @SerialName("legalAddress")
    val legalAddress: String,
    @SerialName("actualAddress")
    val actualAddress: String,
    @SerialName("checkingAccount")
    val checkingAccount: String,
    @SerialName("correspondentAccount")
    val correspondentAccount: String,
    @SerialName("bank")
    val bank: String,
    @SerialName("organisationName")
    val organisationName: String,
    @SerialName("carPlate")
    val carPlate: String,
    @SerialName("carModel")
    val carModel: String,
    @SerialName("carCategory")
    val carCategory: String,
    @SerialName("carPalletCapacity")
    val carCapacity: Int,
    @SerialName("carLoadCapacity")
    val carLoadCapacity: Double?
)
