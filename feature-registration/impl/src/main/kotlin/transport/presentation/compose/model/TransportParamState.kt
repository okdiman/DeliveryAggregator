package transport.presentation.compose.model

import androidx.annotation.StringRes
import presentation.AddressUiModel
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R
import view.model.DefaultParamState

sealed class TransportParamState(
    override val stateText: String,
    override val isError: Boolean,
    @StringRes override val stateError: Int
) : DefaultParamState(stateText, isError, stateError) {
    data class LicencePlateState(
        val text: String = "",
        val isLicencePlateError: Boolean = false,
        @StringRes val error: Int = R.string.license_plate_error
    ) : TransportParamState(text, isLicencePlateError, error)

    data class DepartureAddressState(
        val text: String = "",
        val address: AddressUiModel? = null,
        val isDepartureAddressError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : TransportParamState(text, isDepartureAddressError, error)

    data class CarBrandState(
        val text: String = "",
        val isCarBrandError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : TransportParamState(text, isCarBrandError, error)

    data class CarCategoryState(
        val text: String = "",
        val isCarCategoryError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : TransportParamState(text, isCarCategoryError, error)

    data class CarLoadCapacityState(
        val text: String = "",
        val isCarLoadCapacityError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : TransportParamState(text, isCarLoadCapacityError, error)

    data class CarCapacityState(
        val text: String = "",
        val isCarCapacityError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : TransportParamState(text, isCarCapacityError, error)

    data class BsAddressState(
        val text: String = "",
        val address: AddressUiModel? = null,
        val isBsAddressError: Boolean = false,
        @StringRes val error: Int = R.string.incorrect_address
    ) : TransportParamState(text, isBsAddressError, error)
}
