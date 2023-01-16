package root.data.model.response

import com.google.gson.annotations.SerializedName

class ProfileDto(
    @SerializedName("user")
    val user: ProfileUserDto,
    @SerializedName("contractor")
    val contractor: ProfileContractorDto
)