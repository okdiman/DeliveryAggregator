package presentation

import androidx.annotation.StringRes
import presentation.model.AddressUiModel
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R
import view.model.DefaultParamState

data class AddressState(
    override val stateText: String = "",
    override val isFillingError: Boolean = false,
    override val isValidationError: Boolean = false,
    @StringRes override val fillingErrorDiscription: Int = R.string.incorrect_address,
    @StringRes override val validationErrorDiscription: Int = R.string.empty_error,
    val address: AddressUiModel? = null,
    val isSuggestLoading: Boolean = false
) : DefaultParamState(
    stateText,
    isFillingError,
    isValidationError,
    fillingErrorDiscription,
    validationErrorDiscription
)