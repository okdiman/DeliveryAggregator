package transport.presentation.compose.model

import androidx.annotation.StringRes
import presentation.model.AddressUiModel
import view.model.DefaultParamState
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

sealed class TransportParamState(
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
    data class LicencePlateState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.license_plate_error,
        @StringRes override val validationErrorDiscription: Int = R_core.string.license_plate_symbols_error
    ) : TransportParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class DepartureAddressState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R_core.string.choose_address_from_suggest,
        val address: AddressUiModel? = null
    ) : TransportParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class CarBrandState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R_core.string.car_brand_error
    ) : TransportParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class CarCategoryState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R_core.string.car_category_error
    ) : TransportParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class CarLoadCapacityState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R_core.string.empty_error
    ) : TransportParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )

    data class CarCapacityState(
        override val stateText: String = "",
        override val isFillingError: Boolean = false,
        override val isValidationError: Boolean = false,
        @StringRes override val fillingErrorDiscription: Int = R_core.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R_core.string.empty_error
    ) : TransportParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )
}