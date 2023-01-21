package user.presentation.compose.model

import androidx.annotation.StringRes
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R
import view.model.DefaultParamState

sealed class UserParamState(
    override val stateText: String,
    override val isFillingError: Boolean,
    override val isValidationError: Boolean,
    @StringRes override val fillingErrorDiscription: Int,
    @StringRes override val validationErrorDiscription: Int
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
    ) : UserParamState(
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
    ) : UserParamState(
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
    ) : UserParamState(
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
    ) : UserParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )
}