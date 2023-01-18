package root.presentation.compose.model

data class RouteOrderUiModel(
    val id: Long,
    val arrivalDate: String,
    val status: RouteOrderStatusUiModel?,
    val departureAddress: String,
    val deliveryAddress: String
)