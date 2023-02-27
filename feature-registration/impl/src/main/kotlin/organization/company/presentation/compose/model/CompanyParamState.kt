package organization.company.presentation.compose.model

import androidx.annotation.StringRes
import presentation.AddressSuggestUiModel
import trinity_monsters.delivery_aggregator.feature_registration.impl.R
import view.model.DefaultParamState
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

sealed class CompanyParamState(
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
        @StringRes override val fillingErrorDiscription: Int = R_core.string.common_few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.company_name_error
    ) : CompanyParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class InnState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.company_inn_error,
        @StringRes override val validationErrorDiscription: Int = R.string.company_inn_validate_error
    ) : CompanyParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class KppState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.company_kpp_error,
        @StringRes override val validationErrorDiscription: Int = R.string.company_kpp_validate_error
    ) : CompanyParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class OgrnState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R.string.company_ogrn_error,
        @StringRes override val validationErrorDiscription: Int = R.string.company_ogrn_validate_error
    ) : CompanyParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class ActualAddressState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.common_incorrect_address,
        @StringRes override val validationErrorDiscription: Int = R_core.string.common_empty_error,
        val address: AddressSuggestUiModel? = null,
    ) : CompanyParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class LegalAddressState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.common_incorrect_address,
        @StringRes override val validationErrorDiscription: Int = R_core.string.common_empty_error,
        val address: AddressSuggestUiModel? = null
    ) : CompanyParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )
}