package orderdetails.loadingstate.presentation.compose.model

import androidx.annotation.StringRes
import orderdetails.loadingstate.model.OrderLoadingCargoType
import trinity_monsters.delivery_aggregator.core_ui.R
import view.model.DefaultParamState

sealed class OrderLoadingParamState(
    override val stateText: String,
    override val isFillingError: Boolean = false,
    override val isValidationError: Boolean = false,
    @StringRes override val fillingErrorDiscription: Int = R.string.common_empty_error,
    @StringRes override val validationErrorDiscription: Int = R.string.common_empty_error
) : DefaultParamState(
    stateText,
    isFillingError,
    isValidationError,
    fillingErrorDiscription,
    validationErrorDiscription
) {
    data class BoxesCountState(
        override val stateText: String = ""
    ) : OrderLoadingParamState(stateText)

    data class PalletsCountState(
        override val stateText: String = ""
    ) : OrderLoadingParamState(stateText)

    data class CargoTypeState(
        override val stateText: String = "",
        val cargoType: OrderLoadingCargoType? = null
    ) : OrderLoadingParamState(stateText)

    data class AdditionalOptionsState(
        override val stateText: String = "",
        val optionsList: List<String> = emptyList()
    ) : OrderLoadingParamState(stateText)
}