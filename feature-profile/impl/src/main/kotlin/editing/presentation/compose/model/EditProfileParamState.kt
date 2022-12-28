package editing.presentation.compose.model

import androidx.annotation.StringRes
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R
import view.model.DefaultParamState

sealed class EditProfileParamState(
    stateText: String,
    override val isError: Boolean,
    @StringRes override val stateError: Int
) : DefaultParamState(stateText, isError, stateError) {
    data class NameState(
        val text: String = "",
        val isNameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : EditProfileParamState(text, isNameError, error)

    data class SurnameState(
        val text: String = "",
        val isSurnameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : EditProfileParamState(text, isSurnameError, error)

    data class SecondNameState(
        val text: String = "",
        val isSecondNameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : EditProfileParamState(text, isSecondNameError, error)

    data class EmailState(
        val text: String = "",
        val isEmailError: Boolean = false,
        @StringRes val error: Int = R.string.user_email_error
    ) : EditProfileParamState(text, isEmailError, error)

    data class PhoneState(
        val text: String = "",
        val isPhoneError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : EditProfileParamState(text, isPhoneError, error)

    data class OrganizationNameState(
        val text: String = "",
        val isOrganizationNameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : EditProfileParamState(text, isOrganizationNameError, error)
}