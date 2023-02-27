package root.presentation.compose.model

data class OrderClientUiModel(
    override val id: Long,
    override val arrivalDate: String,
    override val departureAddress: String,
    override val deliveryAddress: String,
    val status: OrderStatusUiModel?,
    val statusCategory: OrderStatusCategoryUiModel?
) : OrderUiModel {
    override val statusText: Int? = status?.text
}