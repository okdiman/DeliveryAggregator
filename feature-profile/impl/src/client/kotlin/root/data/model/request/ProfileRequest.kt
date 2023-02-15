package root.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    val email: String,
    val name: String,
    val surname: String,
    val secondName: String,
    val organisationName: String
)