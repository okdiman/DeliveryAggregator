package root.data.model.response.companyinfo

import kotlinx.serialization.Serializable

@Serializable
data class CompanyDetailsDto(
    val address: CompanyAddressDto,
    val kpp: String,
    val ogrn: String
)