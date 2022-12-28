package view.model

import androidx.annotation.StringRes

abstract class DefaultParamState(
    open val stateText: String,
    open val isError: Boolean,
    @StringRes open val stateError: Int
)