package data.model.response

import com.google.gson.annotations.SerializedName

class ProfileResponse(
    @SerializedName("contractor")
    val dto: ProfileDto
)