package orderdetails.loadingstate.presentation.compose.model

import cargotype.domain.model.CargoType
import view.model.DefaultParamState

sealed class OrderLoadingParamState(
    override val stateText: String
) : DefaultParamState(stateText) {
    data class BoxesCountState(override val stateText: String = "") : OrderLoadingParamState(stateText)
    data class PalletsCountState(override val stateText: String = "") : OrderLoadingParamState(stateText)
    data class CargoTypeState(
        override val stateText: String = "",
        val cargoType: CargoType? = null
    ) : OrderLoadingParamState(stateText)
}