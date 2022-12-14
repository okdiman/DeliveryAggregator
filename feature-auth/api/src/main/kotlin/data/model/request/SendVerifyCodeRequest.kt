package data.model.request

import com.google.gson.annotations.SerializedName

@JvmInline
value class SendVerifyCodeRequest(
    @SerializedName("phone")
    val phone: String
)