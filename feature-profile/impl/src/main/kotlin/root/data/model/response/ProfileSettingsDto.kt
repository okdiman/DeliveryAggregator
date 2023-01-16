package root.data.model.response

import com.google.gson.annotations.SerializedName

class ProfileSettingsDto(
    @SerializedName("push")
    val push: Boolean,
    @SerializedName("sms")
    val sms: Boolean
)