package root.data.model.response

import com.google.gson.annotations.SerializedName

class AuthSuccessDto(
    @SerializedName("token")
    val token: String,
    @SerializedName("tokenInfo")
    val tokenInfo: AuthTokenInfoDto?
)