package domain.model

@Suppress("LongParameterList")
data class ProfileModel(
    val email: String,
    val name: String,
    val surname: String,
    val secondName: String,
    val organizationName: String,
    val phone: String
)