package organization.bank.presentation.compose.model

import androidx.annotation.StringRes
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R
import view.model.DefaultParamState
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

sealed class BankParamState(
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
    data class PaymentAccState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.bank_payment_acc_error,
        @StringRes override val validationErrorDiscription: Int = R.string.bank_payment_acc_validate_error
    ) : BankParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class CorrAccState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.bank_corr_acc_error,
        @StringRes override val validationErrorDiscription: Int = R.string.bank_corr_acc_validate_error
    ) : BankParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class BikState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.bank_bik_error,
        @StringRes override val validationErrorDiscription: Int = R.string.bank_bik_validate_error
    ) : BankParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class BankNameState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.common_few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.bank_name_validate_error
    ) : BankParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )
}