package data.model

import com.google.gson.annotations.SerializedName

class ImageUploadingDto(
    @SerializedName("file")
    val link: String
)