package orderchanges.domain.model

data class OrderChangesModel(
    val orderId: Long,
    val before: OrderChangedValuesModel,
    val after: OrderChangedValuesModel,
)
