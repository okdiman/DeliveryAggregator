package view.model

import androidx.annotation.StringRes

open class DefaultParamState(
    open val stateText: String,
    open val isError: Boolean,
    @StringRes open val stateError: Int
)