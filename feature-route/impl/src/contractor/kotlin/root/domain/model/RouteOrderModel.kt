package root.domain.model

import orderdetails.root.domain.model.details.OrderDetailsClientModel

data class RouteOrderModel(
    val details: OrderModel,
    val index: Int,
    val client: OrderDetailsClientModel
)
