package orderdetails.loadingstate.presentation.compose.model

import orderdetails.cargotype.domain.model.OrderLoadingCargoType
import view.model.DefaultParamState

sealed class OrderLoadingParamState(
    override val stateText: String
) : DefaultParamState(stateText) {
    data class BoxesCountState(override val stateText: String = "") : OrderLoadingParamState(stateText)
    data class PalletsCountState(override val stateText: String = "") : OrderLoadingParamState(stateText)
    data class CargoTypeState(
        override val stateText: String = "",
        val cargoType: OrderLoadingCargoType? = null
    ) : OrderLoadingParamState(stateText)

    data class AdditionalOptionsState(
        override val stateText: String = "",
        val optionsList: List<String> = emptyList()
    ) : OrderLoadingParamState(stateText)
}