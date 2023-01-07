package transport.presentation.compose.model

import androidx.annotation.StringRes
import presentation.model.AddressUiModel
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R
import view.model.DefaultParamState

sealed class TransportProfileParamState(
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
        @StringRes override val fillingErrorDiscription: Int = R.string.license_plate_error,
        @StringRes override val validationErrorDiscription: Int = R.string.license_plate_symbols_error
    ) : TransportProfileParamState(
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
        @StringRes override val fillingErrorDiscription: Int = R.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.choose_address_from_suggest,
        val address: AddressUiModel? = null
    ) : TransportProfileParamState(
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
        @StringRes override val fillingErrorDiscription: Int = R.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.car_brand_error
    ) : TransportProfileParamState(
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
        @StringRes override val fillingErrorDiscription: Int = R.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.car_category_error
    ) : TransportProfileParamState(
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
        @StringRes override val fillingErrorDiscription: Int = R.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.empty_error
    ) : TransportProfileParamState(
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
        @StringRes override val fillingErrorDiscription: Int = R.string.few_symbols_error,
        @StringRes override val validationErrorDiscription: Int = R.string.empty_error
    ) : TransportProfileParamState(
        stateText,
        isFillingError,
        isValidationError,
        fillingErrorDiscription,
        validationErrorDiscription
    )
}