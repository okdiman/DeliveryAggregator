package root.domain.model

import orderdetails.root.domain.model.OrderDetailsModel
import orderdetails.root.domain.model.details.OrderDetailsContractorModel

data class OrderModel(
    val details: OrderDetailsModel,
    val contractor: OrderDetailsContractorModel?,
)
