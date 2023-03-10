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
    @SerialName("organisationName")
    val organisationName: String
)
