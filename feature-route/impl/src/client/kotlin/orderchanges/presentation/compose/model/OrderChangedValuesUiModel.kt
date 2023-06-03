package orderchanges.presentation.compose.model

import cargotype.domain.model.CargoType
import extras.presentation.model.ExtrasUiModel
import java.math.BigDecimal

data class OrderChangedValuesUiModel(
    val boxes: Int,
    val pallets: Int,
    val cargoType: CargoType,
    val price: BigDecimal,
    val extras: List<ExtrasUiModel>,
)
