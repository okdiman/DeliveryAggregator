package root.data.model.response.bankinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BankInfoDetailsDto(
    @SerialName("correspondent_account")
    val correspondentAccount: String
)