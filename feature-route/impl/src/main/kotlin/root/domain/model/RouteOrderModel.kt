package root.domain.model

import orderdetails.root.domain.model.OrderDetailsModel
import orderdetails.root.domain.model.details.OrderDetailsClientModel

data class RouteOrderModel(
    val details: OrderDetailsModel,
    val client: OrderDetailsClientModel
)
