package root.presentation.compose.model

data class OrderRequestUiModel(
    override val id: Long,
    override val arrivalDate: String,
    val status: OrderStatusUiModel?,
    val statusCategory: OrderStatusCategoryUiModel?,
    override val departureAddress: String,
    override val deliveryAddress: String,
) : OrderUiModel {
    override val statusText: Int? = status?.text
}
