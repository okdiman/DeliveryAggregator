package root.data.model.response

import com.google.gson.annotations.SerializedName

@JvmInline
value class AuthSuccessResponse(
    @SerializedName("token")
    val token: String
)