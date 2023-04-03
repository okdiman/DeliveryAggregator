package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ImageUploadingDto(
    @SerialName("file")
    val link: String
)
