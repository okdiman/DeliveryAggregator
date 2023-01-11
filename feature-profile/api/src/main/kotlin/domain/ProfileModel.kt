package domain

@Suppress("LongParameterList")
data class ProfileModel(
    val email: String,
    val name: String,
    val surname: String,
    val secondName: String,
    val inn: String,
    val kpp: String,
    val ogrn: String,
    val bik: String,
    val legalAddress: String,
    val actualAddress: String,
    val checkingAccount: String,
    val correspondentAccount: String,
    val bank: String,
    val organizationName: String,
    val licencePlate: String,
    val carModel: String,
    val carCategory: String,
    val carCapacity: Int,
    val carLoadCapacity: Double?,
    val phone: String
)