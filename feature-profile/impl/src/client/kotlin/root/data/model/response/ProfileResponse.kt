package root.data.model.response

import com.google.gson.annotations.SerializedName

class ProfileResponse(
    @SerializedName("UserClient")
    val dto: ProfileDto
)