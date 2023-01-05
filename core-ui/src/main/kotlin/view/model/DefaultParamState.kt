package view.model

import androidx.annotation.StringRes

open class DefaultParamState(
    open val stateText: String,
    open val isFillingError: Boolean,
    open val isValidationError: Boolean,
    @StringRes open val fillingErrorDiscription: Int,
    @StringRes open val validationErrorDiscription: Int
)