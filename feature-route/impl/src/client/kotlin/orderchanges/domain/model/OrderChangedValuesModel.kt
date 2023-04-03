package orderchanges.domain.model

import cargotype.domain.model.CargoType
import root.domain.model.extras.OrderExtrasModel

data class OrderChangedValuesModel(
    val boxes: Int,
    val pallets: Int,
    val cargoType: CargoType,
    val price: Int,
    val extras: List<OrderExtrasModel>,
)
