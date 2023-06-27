package root.data.model.response.companyinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyInfoDto(
    @SerialName("data")
    val details: CompanyDetailsDto,
    @SerialName("value")
    val companyName: String
)