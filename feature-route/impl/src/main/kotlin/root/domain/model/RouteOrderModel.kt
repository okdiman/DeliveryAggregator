package root.domain.model

import orderdetails.domain.model.details.OrderDetailsClientModel
import orderdetails.domain.model.OrderDetailsModel

data class RouteOrderModel(
    val details: OrderDetailsModel,
    val index: Int,
    val client: OrderDetailsClientModel
)