package neworder.root.presentation.compose.model

import androidx.annotation.StringRes
import cargotype.domain.model.CargoType
import root.presentation.compose.model.RouteStorageModel
import trinity_monsters.delivery_aggregator.core_ui.R
import view.model.DefaultParamState
import java.util.Date

sealed class NewOrderParamState(
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
    data class BoxesCountState(override val stateText: String = "") : NewOrderParamState(stateText)
    data class PalletsCountState(override val stateText: String = "") : NewOrderParamState(stateText)
    data class CargoTypeState(
        override val stateText: String = "",
        val cargoType: CargoType? = null
    ) : NewOrderParamState(stateText)

    data class ArrivalDateState(
        override val stateText: String = "",
        val date: Date? = null
    ) : NewOrderParamState(stateText)

    data class ArrivalTimeState(override val stateText: String = "") : NewOrderParamState(stateText)
    data class StorageState(
        override val stateText: String = "",
        val storage: RouteStorageModel? = null
    ) : NewOrderParamState(stateText)

    data class WeightState(override val stateText: String = "") : NewOrderParamState(stateText)
    data class CommentState(override val stateText: String = "") : NewOrderParamState(stateText)
    data class MarketplaceState(override val stateText: String = "") : NewOrderParamState(stateText) {
        companion object {
            val DEFAULT = MarketplaceState("Wildberries")
        }
    }

    data class CreateButtonState(
        override val stateText: String = "",
        val subtitle: String = "",
        val isEnabled: Boolean = false
    ) : NewOrderParamState(stateText)
}