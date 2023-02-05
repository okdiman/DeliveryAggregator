package root.domain.model

import orderdetails.root.domain.model.details.OrderDetailsClientModel
import orderdetails.root.domain.model.OrderDetailsModel

data class RouteOrderModel(
    val details: OrderDetailsModel,
    val index: Int,
    val client: OrderDetailsClientModel
)