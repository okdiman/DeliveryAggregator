package root.presentation.compose.model

data class RouteOrderUiModel(
    override val id: Long,
    override val arrivalDate: String,
    val status: RouteOrderStatusUiModel?,
    override val departureAddress: String,
    override val deliveryAddress: String,
    override val statusText: Int? = status?.text
) : OrderUiModel