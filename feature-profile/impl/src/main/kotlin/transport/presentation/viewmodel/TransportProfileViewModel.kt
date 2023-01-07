package transport.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import data.AddressConstants
import di.modules.DIGITS_AND_LETTERS_VALIDATOR_QUALIFIER
import di.modules.LETTERS_VALIDATOR_QUALIFIER
import di.modules.LICENCE_PLATE_VALIDATOR_QUALIFIER
import domain.model.AddressSuggestRequestModel
import domain.usecase.GetSuggestByQueryUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import presentation.TransportProfileParameters
import presentation.mapper.AddressSuggestUiMapper
import presentation.model.AddressUiModel
import transport.presentation.viewmodel.model.TransportProfileAction
import transport.presentation.viewmodel.model.TransportProfileEvent
import transport.presentation.viewmodel.model.TransportProfileState
import utils.CommonConstants.LIMITS.Common.MIN_ADDRESS_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_BRAND_MIN_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_INFO_MIN_CHARS
import utils.CommonConstants.LIMITS.Transport.LICENCE_PLATE_MIN_CHARS
import utils.isTextFieldFilled
import utils.validators.TextFieldValidator

class TransportProfileViewModel(
    private val parameters: TransportProfileParameters
) : BaseViewModel<TransportProfileState, TransportProfileAction, TransportProfileEvent>(
    initialState = TransportProfileState()
), KoinComponent {

    private val getSuggestByQuery by inject<GetSuggestByQueryUseCase>()
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

    init {
        viewState = viewState.copy(
            licencePlate = viewState.licencePlate.copy(stateText = parameters.profileModel.licencePlate),
            carCapacity = viewState.carCapacity.copy(stateText = parameters.profileModel.carCapacity.toString()),
            carCategory = viewState.carCategory.copy(stateText = parameters.profileModel.carCategory),
            carBrand = viewState.carBrand.copy(stateText = parameters.profileModel.carModel),
            carLoadCapacity = viewState.carLoadCapacity.copy(
                stateText = parameters.profileModel.carLoadCapacity.toString()
            ),
            //TODO какая то фигня с адресом, где полный
            departureAddress = viewState.departureAddress.copy(stateText = parameters.profileModel.legalAddress)
        )
    }

    override fun obtainEvent(viewEvent: TransportProfileEvent) {
        when (viewEvent) {
            is TransportProfileEvent.OnLicencePlateChanged -> onLicencePlateChanged(viewEvent.licencePlate)
            is TransportProfileEvent.OnBSAddressChanged -> onBsAddressChanged(viewEvent.bsAddress)
            is TransportProfileEvent.OnCarBrandChanged -> onCarBrandChanged(viewEvent.carBrand)
            is TransportProfileEvent.OnCarLoadCapacityChanged -> onCarLoadCapacityChanged(viewEvent.carLoadCapacity)
            is TransportProfileEvent.OnCarCategoryChanged -> onCarCategoryChanged(viewEvent.carCategory)
            is TransportProfileEvent.OnCarCapacityChanged -> onCarCapacityChanged(viewEvent.carCapacity)
            is TransportProfileEvent.OnSuggestAddressClick -> onSuggestAddressClick(viewEvent.address)
            TransportProfileEvent.OnBackButtonClick -> onBackButtonClick()
            TransportProfileEvent.OnSaveButtonClick -> onSaveButtonClick()
            TransportProfileEvent.OnDepartAddressClick -> onDepartAddressClick()
            TransportProfileEvent.ResetAction -> onResetAction()
        }
    }

    private fun onDepartAddressClick() {
        viewAction = TransportProfileAction.OpenDepartureAddressBs
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
            isSaveButtonVisible = isSaveButtonVisible(
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
            isSaveButtonVisible = isSaveButtonVisible(
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
            isSaveButtonVisible = isSaveButtonVisible(
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
            isSaveButtonVisible = isSaveButtonVisible(
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
            isSaveButtonVisible = isSaveButtonVisible(
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
            isSaveButtonVisible = isSaveButtonVisible(
                viewState.copy(carCapacity = viewState.carCapacity.copy(stateText = newCarCapacity))
            )
        )
    }

    private fun onBackButtonClick() {
        viewAction = TransportProfileAction.OpenPreviousScreen
    }

    private fun onSaveButtonClick() {
        viewAction = TransportProfileAction.OpenPreviousScreen
    }

    private fun loadSuggests(query: String) {
        suggestJob?.cancel()
        if (query.length >= AddressConstants.MIN_CHARS_FOR_SUGGEST) {
            suggestJob = launchJob(context = appDispatchers.network, onError = {
                viewState = viewState.copy(
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
                )
            }) {
                delay(AddressConstants.DEBOUNCE)
                viewState = viewState.copy(
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = true)
                )
                val suggests = getSuggestByQuery(AddressSuggestRequestModel(query = query))
                viewState = viewState.copy(
                    suggests = addressUiMapper.map(suggests),
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
                )
            }
        } else {
            viewState = viewState.copy(suggests = emptyList())
        }
    }

    private fun isSaveButtonVisible(state: TransportProfileState) =
        isTextFieldFilled(state.licencePlate.stateText, LICENCE_PLATE_MIN_CHARS) &&
                isTextFieldFilled(state.departureAddress.stateText, MIN_ADDRESS_CHARS) &&
                isTextFieldFilled(state.carBrand.stateText, CAR_BRAND_MIN_CHARS) &&
                isTextFieldFilled(state.carCategory.stateText, CAR_INFO_MIN_CHARS) &&
                isTextFieldFilled(state.carLoadCapacity.stateText, CAR_INFO_MIN_CHARS) &&
                isTextFieldFilled(state.carCapacity.stateText, CAR_INFO_MIN_CHARS) &&
                !state.licencePlate.isValidationError && !state.carCategory.isValidationError &&
                !state.carBrand.isValidationError && isTransportChanged(state)

    private fun isTransportChanged(state: TransportProfileState) =
        //TODO departureAddress опять проблема
        parameters.profileModel != parameters.profileModel.copy(
            licencePlate = state.licencePlate.stateText,
            carModel = state.carBrand.stateText,
            carCapacity = state.carCapacity.stateText.toInt(),
            carCategory = state.carCategory.stateText,
            carLoadCapacity = state.carLoadCapacity.stateText.toInt()
        )
}