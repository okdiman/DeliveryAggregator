package domain.model

@Suppress("LongParameterList")
class AuthSignUpModel(
    val code: Int,
    val phone: String,
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
    val organisationName: String,
    val carPlate: String,
    val address: AuthAddressSignUpModel,
    val carModel: String,
    val carCategory: String,
    val carLoadCapacity: Double,
    val carPalletCapacity: Int
)