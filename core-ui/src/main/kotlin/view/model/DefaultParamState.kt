package view.model

import androidx.annotation.StringRes
import trinity_monsters.delivery_aggregator.core_ui.R

open class DefaultParamState(
    open val stateText: String,
    open val isFillingError: Boolean = false,
    open val isValidationError: Boolean = false,
    @StringRes open val fillingErrorDiscription: Int = R.string.common_empty_error,
    @StringRes open val validationErrorDiscription: Int = R.string.common_empty_error
)