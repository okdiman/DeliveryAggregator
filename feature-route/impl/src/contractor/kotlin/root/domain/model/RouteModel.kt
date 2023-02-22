package root.domain.model

data class RouteModel(
    val id: Long,
    val orders: List<RouteOrderModel>,
    val price: Int,
    val distance: Double,
    val status: RouteStatusProgress
)