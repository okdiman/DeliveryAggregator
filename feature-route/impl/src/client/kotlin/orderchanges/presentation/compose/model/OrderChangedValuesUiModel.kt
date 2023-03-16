package orderchanges.presentation.compose.model

import cargotype.domain.model.CargoType
import extras.presentation.model.ExtrasUiModel

data class OrderChangedValuesUiModel(
    val boxes: Int,
    val pallets: Int,
    val cargoType: CargoType,
    val price: Int,
    val extras: List<ExtrasUiModel>,
)
