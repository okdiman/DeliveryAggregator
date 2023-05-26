package orderchanges.domain.model

import cargotype.domain.model.CargoType
import root.domain.model.extras.OrderExtrasModel
import java.math.BigDecimal

data class OrderChangedValuesModel(
    val boxes: Int,
    val pallets: Int,
    val cargoType: CargoType,
    val price: BigDecimal,
    val extras: List<OrderExtrasModel>,
)
