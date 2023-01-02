package transport.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import data.AddressConstants.DEBOUNCE
import data.AddressConstants.MIN_CHARS_FOR_SUGGEST
import domain.model.AddressSuggestRequestModel
import domain.usecase.GetSuggestByQueryUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.model.AddressUiModel
import presentation.mapper.AddressSuggestUiMapper
import presentation.parameters.TransportParameters
import transport.presentation.viewmodel.model.TransportAction
import transport.presentation.viewmodel.model.TransportEvent
import transport.presentation.viewmodel.model.TransportState
import utils.CommonConstants.LIMITS.Common.MIN_ADDRESS_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_BRAND_MIN_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_INFO_MIN_CHARS
import utils.CommonConstants.LIMITS.Transport.LICENCE_PLATE_MIN_CHARS
import utils.isTextFieldFilled

class TransportViewModel(
    private val parameters: TransportParameters
) : BaseViewModel<TransportState, TransportAction, TransportEvent>(initialState = TransportState()),
    KoinComponent {

    private val getSuggestByQuery by inject<GetSuggestByQueryUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val addressUiMapper by inject<AddressSuggestUiMapper>()

    private var suggestJob: Job? = null

    override fun obtainEvent(viewEvent: TransportEvent) {
        when (viewEvent) {
            is TransportEvent.OnLicencePlateChanged -> onLicencePlateChanged(viewEvent.licencePlate)
            is TransportEvent.OnBSAddressChanged -> onBsAddressChanged(viewEvent.bsAddress)
            is TransportEvent.OnCarBrandChanged -> onCarBrandChanged(viewEvent.carBrand)
            is TransportEvent.OnCarLoadCapacityChanged -> onCarLoadCapacityChanged(viewEvent.carLoadCapacity)
            is TransportEvent.OnCarCategoryChanged -> onCarCategoryChanged(viewEvent.carCategory)
            is TransportEvent.OnCarCapacityChanged -> onCarCapacityChanged(viewEvent.carCapacity)
            is TransportEvent.OnSuggestAddressClick -> onSuggestAddressClick(viewEvent.address)
            TransportEvent.OnBackButtonClick -> onBackButtonClick()
            TransportEvent.OnContinueButtonClick -> onContinueButtonClick()
            TransportEvent.OnDepartAddressClick -> onDepartAddressClick()
            TransportEvent.ResetAction -> onResetAction()
        }
    }

    private fun onDepartAddressClick() {
        viewAction = TransportAction.OpenDepartureAddressBs
    }

    private fun onSuggestAddressClick(address: AddressUiModel) {
        viewState = viewState.copy(
            departureAddress = viewState.departureAddress.copy(
                text = address.value,
                address = address,
                isDepartureAddressError = address.house.isEmpty()
            ),
            bsAddress = viewState.bsAddress.copy(
                text = address.subtitle
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(departureAddress = address.value)
        )
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

    private fun onBsAddressChanged(address: String) {
        loadSuggests(address)
        viewState = viewState.copy(
            bsAddress = viewState.bsAddress.copy(
                text = address
            )
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

    private fun loadSuggests(query: String) {
        suggestJob?.cancel()
        if (query.length >= MIN_CHARS_FOR_SUGGEST) {
            suggestJob = launchJob(context = appDispatchers.network, onError = {
                viewState = viewState.copy(
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
                )
            }) {
                delay(DEBOUNCE)
                viewState = viewState.copy(
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = true)
                )
                val suggests = getSuggestByQuery(
                    AddressSuggestRequestModel(
                        query = query,
                        code = parameters.user.code.toInt(),
                        phone = parameters.user.phone
                    )
                )
                viewState = viewState.copy(
                    suggests = addressUiMapper.map(suggests),
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
                )
            }
        } else {
            viewState = viewState.copy(suggests = emptyList())
        }
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