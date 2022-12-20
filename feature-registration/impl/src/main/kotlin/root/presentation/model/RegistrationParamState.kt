package root.presentation.model

import androidx.annotation.StringRes

open class RegistrationParamState(
    open val stateText: String,
    open val isError: Boolean,
    @StringRes open val stateError: Int
)