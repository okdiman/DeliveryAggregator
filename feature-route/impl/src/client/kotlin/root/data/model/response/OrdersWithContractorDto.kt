package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import orderdetails.root.data.model.OrderDetailsDto

@Serializable
data class OrdersWithContractorDto(
    @SerialName("request")
    val order: OrderDetailsDto,
    @SerialName("contractor")
    val contractor: ContractorDto?,
)
