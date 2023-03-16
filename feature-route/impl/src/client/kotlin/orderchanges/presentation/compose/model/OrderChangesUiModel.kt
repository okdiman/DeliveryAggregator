package orderchanges.presentation.compose.model

data class OrderChangesUiModel(
    val orderId: Long,
    val before: OrderChangedValuesUiModel,
    val after: OrderChangedValuesUiModel,
)
