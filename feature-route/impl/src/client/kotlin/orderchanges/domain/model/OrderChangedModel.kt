package orderchanges.domain.model

data class OrderChangedModel(
    val orderId: Long,
    val before: OrderChangedValuesModel,
    val after: OrderChangedValuesModel,
)
