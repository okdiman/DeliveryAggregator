package editing.presentation.compose.model

import androidx.annotation.StringRes
import trinity_monsters.delivery_aggregator.core_ui.R
import view.model.DefaultParamState

sealed class EditProfileParamState(
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
    data class NameState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.common_few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.user_name_error
    ) : EditProfileParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class SurnameState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.common_few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.user_surname_error
    ) : EditProfileParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class SecondNameState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.common_few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.user_second_name_error
    ) : EditProfileParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class EmailState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.common_few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.user_email_error
    ) : EditProfileParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class PhoneState(
        override val stateText: String = ""
    ) : EditProfileParamState(stateText)

    data class OrganizationNameState(
        override val stateText: String = ""
    ) : EditProfileParamState(stateText)
}