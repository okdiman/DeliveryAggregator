package root.domain.model

import java.math.BigDecimal

data class RouteModel(
    val id: Long,
    val orders: List<RouteOrderModel>,
    val price: BigDecimal,
    val distance: Double,
    val status: RouteStatusProgress
)
