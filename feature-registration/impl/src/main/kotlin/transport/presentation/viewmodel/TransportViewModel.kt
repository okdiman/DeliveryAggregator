package transport.presentation.viewmodel

import BaseViewModel
import root.RegistrationConstants.Limits.Common.MIN_ADDRESS_CHARS
import root.RegistrationConstants.Limits.Transport.CAR_BRAND_MIN_CHARS
import root.RegistrationConstants.Limits.Transport.CAR_INFO_MIN_CHARS
import root.RegistrationConstants.Limits.Transport.LICENCE_PLATE_MIN_CHARS
import transport.presentation.viewmodel.model.TransportAction
import transport.presentation.viewmodel.model.TransportEvent
import transport.presentation.viewmodel.model.TransportState
import utils.isTextFieldFilled

class TransportViewModel : BaseViewModel<TransportState, TransportAction, TransportEvent>(
    initialState = TransportState()
) {

    override fun obtainEvent(viewEvent: TransportEvent) {
        when (viewEvent) {
            is TransportEvent.OnLicencePlateChanged -> onLicencePlateChanged(viewEvent.licencePlate)
            is TransportEvent.OnDepartureAddressChanged -> onDepartureAddressChanged(viewEvent.departureAddress)
            is TransportEvent.OnCarBrandChanged -> onCarBrandChanged(viewEvent.carBrand)
            is TransportEvent.OnCarLoadCapacityChanged -> onCarLoadCapacityChanged(viewEvent.carLoadCapacity)
            is TransportEvent.OnCarCategoryChanged -> onCarCategoryChanged(viewEvent.carCategory)
            is TransportEvent.OnCarCapacityChanged -> onCarCapacityChanged(viewEvent.carCapacity)
            is TransportEvent.OnBackButtonClick -> onBackButtonClick()
            is TransportEvent.OnContinueButtonClick -> onContinueButtonClick()
            is TransportEvent.ResetAction -> onResetAction()
        }
    }

    private fun onLicencePlateChanged(newLicencePlate: String) {
        viewState = viewState.copy(
            licencePlate = viewState.licencePlate.copy(
                text = newLicencePlate,
                isLicencePlateError = !isTextFieldFilled(
                    newLicencePlate,
                    LICENCE_PLATE_MIN_CHARS
                )
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(licencePlate = newLicencePlate)
        )
    }

    private fun onDepartureAddressChanged(newDepartureAddress: String) {
        viewState = viewState.copy(
            departureAddress = viewState.departureAddress.copy(
                text = newDepartureAddress,
                isDepartureAddressError = !isTextFieldFilled(newDepartureAddress, MIN_ADDRESS_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(departureAddress = newDepartureAddress)
        )
    }

    private fun onCarBrandChanged(newCarBrand: String) {
        viewState = viewState.copy(
            carBrand = viewState.carBrand.copy(
                text = newCarBrand,
                isCarBrandError = !isTextFieldFilled(newCarBrand, CAR_BRAND_MIN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(carBrand = newCarBrand)
        )
    }

    private fun onCarLoadCapacityChanged(newCarLoadCapacity: String) {
        viewState = viewState.copy(
            carLoadCapacity = viewState.carLoadCapacity.copy(
                text = newCarLoadCapacity,
                isCarLoadCapacityError = !isTextFieldFilled(newCarLoadCapacity, CAR_INFO_MIN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(carLoadCapacity = newCarLoadCapacity)
        )
    }

    private fun onCarCategoryChanged(newCarCategory: String) {
        viewState = viewState.copy(
            carCategory = viewState.carCategory.copy(
                text = newCarCategory,
                isCarCategoryError = !isTextFieldFilled(newCarCategory, CAR_INFO_MIN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(carCategory = newCarCategory)
        )
    }

    private fun onCarCapacityChanged(newCarCapacity: String) {
        viewState = viewState.copy(
            carCapacity = viewState.carCapacity.copy(
                text = newCarCapacity,
                isCarCapacityError = !isTextFieldFilled(newCarCapacity, CAR_INFO_MIN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(carCapacity = newCarCapacity)
        )
    }

    private fun onBackButtonClick() {
        viewAction = TransportAction.OpenPreviousStep
    }

    private fun onContinueButtonClick() {
        viewAction = TransportAction.OpenNextStep
    }

    private fun isContinueButtonEnabled(
        licencePlate: String = viewState.licencePlate.text,
        departureAddress: String = viewState.departureAddress.text,
        carBrand: String = viewState.carBrand.text,
        carCategory: String = viewState.carCategory.text,
        carLoadCapacity: String = viewState.carLoadCapacity.text,
        carCapacity: String = viewState.carCapacity.text
    ): Boolean {
        return isTextFieldFilled(licencePlate, LICENCE_PLATE_MIN_CHARS) &&
                isTextFieldFilled(departureAddress, MIN_ADDRESS_CHARS) &&
                isTextFieldFilled(carBrand, CAR_BRAND_MIN_CHARS) &&
                isTextFieldFilled(carCategory, CAR_INFO_MIN_CHARS) &&
                isTextFieldFilled(carLoadCapacity, CAR_INFO_MIN_CHARS) &&
                isTextFieldFilled(carCapacity, CAR_INFO_MIN_CHARS)
    }
}