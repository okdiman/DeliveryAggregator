package root.data.model.response

import com.google.gson.annotations.SerializedName

@Suppress("LongParameterList")
class ProfileInfoDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("secondName")
    val secondName: String,
    @SerializedName("organisationName")
    val organisationName: String
)