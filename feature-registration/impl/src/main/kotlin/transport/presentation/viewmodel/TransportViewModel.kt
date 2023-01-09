package transport.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import data.AddressConstants.DEBOUNCE
import data.AddressConstants.MIN_CHARS_FOR_SUGGEST
import di.modules.DIGITS_AND_LETTERS_VALIDATOR_QUALIFIER
import di.modules.LETTERS_VALIDATOR_QUALIFIER
import di.modules.LICENCE_PLATE_VALIDATOR_QUALIFIER
import domain.GetAuthSuggestByQueryUseCase
import domain.model.request.AuthAddressSuggestRequestModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import presentation.mapper.AddressSuggestUiMapper
import presentation.model.AddressUiModel
import presentation.parameters.TransportParameters
import transport.presentation.viewmodel.model.TransportAction
import transport.presentation.viewmodel.model.TransportEvent
import transport.presentation.viewmodel.model.TransportState
import utils.CommonConstants.LIMITS.Common.MIN_ADDRESS_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_BRAND_MIN_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_INFO_MIN_CHARS
import utils.CommonConstants.LIMITS.Transport.LICENCE_PLATE_MIN_CHARS
import utils.isTextFieldFilled
import utils.validators.TextFieldValidator

class TransportViewModel(
    private val parameters: TransportParameters
) : BaseViewModel<TransportState, TransportAction, TransportEvent>(initialState = TransportState()),
    KoinComponent {

    private val getSuggestByQuery by inject<GetAuthSuggestByQueryUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val addressUiMapper by inject<AddressSuggestUiMapper>()
    private val lettersValidator by inject<TextFieldValidator>(named(LETTERS_VALIDATOR_QUALIFIER))
    private val licencePlateValidator by inject<TextFieldValidator>(
        named(LICENCE_PLATE_VALIDATOR_QUALIFIER)
    )
    private val digitsAndLettersValidator by inject<TextFieldValidator>(
        named(DIGITS_AND_LETTERS_VALIDATOR_QUALIFIER)
    )

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
                stateText = address.value,
                address = address,
                isFillingError = address.house.isEmpty()
            ),
            bsAddress = viewState.bsAddress.copy(
                stateText = address.subtitle
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(
                    departureAddress = viewState.departureAddress.copy(stateText = address.value)
                )
            )
        )
    }

    private fun onLicencePlateChanged(newLicencePlate: String) {
        val isValid = licencePlateValidator.isValidate(newLicencePlate)
        viewState = viewState.copy(
            licencePlate = viewState.licencePlate.copy(
                stateText = newLicencePlate,
                isFillingError = !isTextFieldFilled(newLicencePlate, LICENCE_PLATE_MIN_CHARS),
                isValidationError = !isValid
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(
                    licencePlate = viewState.licencePlate.copy(
                        stateText = newLicencePlate,
                        isValidationError = !isValid
                    )
                )
            )
        )
    }

    private fun onBsAddressChanged(address: String) {
        loadSuggests(address)
        viewState = viewState.copy(
            bsAddress = viewState.bsAddress.copy(
                stateText = address
            )
        )
    }

    private fun onCarBrandChanged(newCarBrand: String) {
        val isValid = lettersValidator.isValidate(newCarBrand)
        viewState = viewState.copy(
            carBrand = viewState.carBrand.copy(
                stateText = newCarBrand,
                isFillingError = !isTextFieldFilled(newCarBrand, CAR_BRAND_MIN_CHARS),
                isValidationError = !isValid
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(
                    carBrand = viewState.carBrand.copy(
                        stateText = newCarBrand,
                        isValidationError = !isValid
                    )
                )
            )
        )
    }

    private fun onCarLoadCapacityChanged(newCarLoadCapacity: String) {
        viewState = viewState.copy(
            carLoadCapacity = viewState.carLoadCapacity.copy(
                stateText = newCarLoadCapacity,
                isFillingError = !isTextFieldFilled(newCarLoadCapacity, CAR_INFO_MIN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(
                    carLoadCapacity = viewState.carLoadCapacity.copy(stateText = newCarLoadCapacity)
                )
            )
        )
    }

    private fun onCarCategoryChanged(newCarCategory: String) {
        val isValid = digitsAndLettersValidator.isValidate(newCarCategory)
        viewState = viewState.copy(
            carCategory = viewState.carCategory.copy(
                stateText = newCarCategory,
                isFillingError = !isTextFieldFilled(newCarCategory, CAR_INFO_MIN_CHARS),
                isValidationError = !isValid
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(
                    carCategory = viewState.carCategory.copy(
                        stateText = newCarCategory,
                        isValidationError = !isValid
                    )
                )
            )
        )
    }

    private fun onCarCapacityChanged(newCarCapacity: String) {
        viewState = viewState.copy(
            carCapacity = viewState.carCapacity.copy(
                stateText = newCarCapacity,
                isFillingError = !isTextFieldFilled(newCarCapacity, CAR_INFO_MIN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(carCapacity = viewState.carCapacity.copy(stateText = newCarCapacity))
            )
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
                    AuthAddressSuggestRequestModel(
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

    private fun isContinueButtonEnabled(state: TransportState): Boolean {
        return isTextFieldFilled(state.licencePlate.stateText, LICENCE_PLATE_MIN_CHARS) &&
                isTextFieldFilled(state.departureAddress.stateText, MIN_ADDRESS_CHARS) &&
                isTextFieldFilled(state.carBrand.stateText, CAR_BRAND_MIN_CHARS) &&
                isTextFieldFilled(state.carCategory.stateText, CAR_INFO_MIN_CHARS) &&
                isTextFieldFilled(state.carLoadCapacity.stateText, CAR_INFO_MIN_CHARS) &&
                isTextFieldFilled(state.carCapacity.stateText, CAR_INFO_MIN_CHARS) &&
                !state.licencePlate.isValidationError && !state.carCategory.isValidationError &&
                !state.carBrand.isValidationError
    }
}