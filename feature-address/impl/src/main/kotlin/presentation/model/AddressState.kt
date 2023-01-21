package presentation.model

import androidx.annotation.StringRes
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R
import view.model.DefaultParamState

data class AddressState(
    override val stateText: String = "",
    override val isFillingError: Boolean = false,
    override val isValidationError: Boolean = false,
    @StringRes override val fillingErrorDiscription: Int = R.string.common_incorrect_address,
    @StringRes override val validationErrorDiscription: Int = R.string.common_empty_error,
    val address: AddressUiModel? = null,
    val isSuggestLoading: Boolean = false
) : DefaultParamState(
    stateText,
    isFillingError,
    isValidationError,
    fillingErrorDiscription,
    validationErrorDiscription
)