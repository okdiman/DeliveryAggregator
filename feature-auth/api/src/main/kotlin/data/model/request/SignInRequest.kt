package data.model.request

import com.google.gson.annotations.SerializedName

class SignInRequest(
    @SerializedName("code")
    val code: Int,
    @SerializedName("phone")
    val phone: String
)