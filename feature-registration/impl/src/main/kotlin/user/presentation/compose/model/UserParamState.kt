package user.presentation.compose.model

import androidx.annotation.StringRes
import root.presentation.model.RegistrationParamState
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R

sealed class UserParamState(
    override val stateText: String,
    override val isError: Boolean,
    @StringRes override val stateError: Int
) : RegistrationParamState(stateText, isError, stateError) {
    data class NameState(
        val text: String = "",
        val isNameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : UserParamState(text, isNameError, error)

    data class SurnameState(
        val text: String = "",
        val isSurnameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : UserParamState(text, isSurnameError, error)

    data class SecondNameState(
        val text: String = "",
        val isSecondNameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : UserParamState(text, isSecondNameError, error)

    data class EmailState(
        val text: String = "",
        val isEmailError: Boolean = false,
        @StringRes val error: Int = R.string.user_email_error
    ) : UserParamState(text, isEmailError, error)
}