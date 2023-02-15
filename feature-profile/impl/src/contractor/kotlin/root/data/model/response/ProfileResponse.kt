package root.data.model.response

import com.google.gson.annotations.SerializedName
import root.data.model.response.ProfileDto

class ProfileResponse(
    @SerializedName("contractor")
    val dto: ProfileDto
)