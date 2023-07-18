package root.data.model.response.bankinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BankInfoDto(
    @SerialName("data")
    val details: BankInfoDetailsDto,
    @SerialName("value")
    val bankName: String
)