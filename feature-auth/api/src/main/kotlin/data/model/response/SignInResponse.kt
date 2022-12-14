package data.model.response

import com.google.gson.annotations.SerializedName

@JvmInline
value class SignInResponse(
    @SerializedName("token")
    val token: String
)