package orderchanges.presentation.mapper

import extras.presentation.mapper.ExtrasUiMapper
import extras.presentation.model.ExtrasUiModel
import orderchanges.domain.model.OrderChangedValuesModel
import orderchanges.domain.model.OrderChangesModel
import orderchanges.presentation.compose.model.OrderChangedValuesUiModel
import orderchanges.presentation.compose.model.OrderChangesUiModel

class OrderChangesUiMapper(
    private val extrasUiMapper: ExtrasUiMapper,
) {

    fun map(model: OrderChangesModel) = OrderChangesUiModel(
        orderId = model.orderId,
        before = mapChangedValues(model.before),
        after = mapChangedValues(model.after),
    )

    private fun mapChangedValues(model: OrderChangedValuesModel) = OrderChangedValuesUiModel(
        boxes = model.boxes,
        pallets = model.pallets,
        cargoType = model.cargoType,
        price = model.price,
        extras = extrasUiMapper.map(model.extras).ifEmpty { listOf(ExtrasUiModel.Default) }
    )
}
