package transport.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import di.modules.DIGITS_AND_LETTERS_VALIDATOR_QUALIFIER
import di.modules.LETTERS_VALIDATOR_QUALIFIER
import di.modules.LICENCE_PLATE_VALIDATOR_QUALIFIER
import di.modules.LOAD_CAPACITY_VALIDATOR_QUALIFIER
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import transport.presentation.TransportProfileParameters
import root.domain.UpdateProfileUseCase
import transport.presentation.viewmodel.model.TransportProfileAction
import transport.presentation.viewmodel.model.TransportProfileEvent
import transport.presentation.viewmodel.model.TransportProfileState
import utils.CommonConstants.LIMITS.Transport.CAR_BRAND_MIN_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_INFO_MIN_CHARS
import utils.CommonConstants.LIMITS.Transport.LICENCE_PLATE_MIN_CHARS
import utils.isTextFieldFilled
import utils.validators.domain.TextFieldValidator

class TransportProfileViewModel(
    private val parameters: TransportProfileParameters
) : BaseViewModel<TransportProfileState, TransportProfileAction, TransportProfileEvent>(
    initialState = TransportProfileState()
), KoinComponent {

    private val appDispatchers by inject<AppDispatchers>()
    private val updateProfile by inject<UpdateProfileUseCase>()
    private val lettersValidator by inject<TextFieldValidator>(named(LETTERS_VALIDATOR_QUALIFIER))
    private val licencePlateValidator by inject<TextFieldValidator>(
        named(LICENCE_PLATE_VALIDATOR_QUALIFIER)
    )
    private val digitsAndLettersValidator by inject<TextFieldValidator>(
        named(DIGITS_AND_LETTERS_VALIDATOR_QUALIFIER)
    )
    private val loadCapacityValidator by inject<TextFieldValidator>(
        named(LOAD_CAPACITY_VALIDATOR_QUALIFIER)
    )

    init {
        viewState = viewState.copy(
            licencePlate = viewState.licencePlate.copy(stateText = parameters.profileModel.licencePlate),
            carCapacity = viewState.carCapacity.copy(stateText = parameters.profileModel.carCapacity.toString()),
            carCategory = viewState.carCategory.copy(stateText = parameters.profileModel.carCategory),
            carBrand = viewState.carBrand.copy(stateText = parameters.profileModel.carModel),
            carLoadCapacity = viewState.carLoadCapacity.copy(
                stateText = parameters.profileModel.carLoadCapacity.toString()
            )
        )
    }

    override fun obtainEvent(viewEvent: TransportProfileEvent) {
        when (viewEvent) {
            is TransportProfileEvent.OnLicencePlateChanged -> onLicencePlateChanged(viewEvent.licencePlate)
            is TransportProfileEvent.OnCarBrandChanged -> onCarBrandChanged(viewEvent.carBrand)
            is TransportProfileEvent.OnCarLoadCapacityChanged -> onCarLoadCapacityChanged(viewEvent.carLoadCapacity)
            is TransportProfileEvent.OnCarCategoryChanged -> onCarCategoryChanged(viewEvent.carCategory)
            is TransportProfileEvent.OnCarCapacityChanged -> onCarCapacityChanged(viewEvent.carCapacity)
            TransportProfileEvent.OnBackButtonClick -> onBackButtonClick()
            TransportProfileEvent.OnSaveButtonClick -> onSaveButtonClick()
        }
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
        val isValid = loadCapacityValidator.isValidate(newCarLoadCapacity)
        viewState = viewState.copy(
            carLoadCapacity = viewState.carLoadCapacity.copy(
                stateText = newCarLoadCapacity,
                isFillingError = !isTextFieldFilled(newCarLoadCapacity, CAR_INFO_MIN_CHARS),
                isValidationError = !isValid
            ),
            isSaveButtonVisible = isSaveButtonVisible(
                viewState.copy(
                    carLoadCapacity = viewState.carLoadCapacity.copy(
                        stateText = newCarLoadCapacity,
                        isValidationError = !isValid
                    )
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
        launchJob(appDispatchers.network) {
            updateProfile(
                parameters.profileModel.copy(
                    licencePlate = viewState.licencePlate.stateText,
                    carCategory = viewState.carCategory.stateText,
                    carModel = viewState.carBrand.stateText,
                    carCapacity = viewState.carCapacity.stateText.toInt(),
                    carLoadCapacity = viewState.carLoadCapacity.stateText.toDoubleOrNull()
                )
            )
            viewAction = TransportProfileAction.OpenProfileWithUpdate
        }
    }

    private fun isSaveButtonVisible(state: TransportProfileState) =
        isTextFieldFilled(state.licencePlate.stateText, LICENCE_PLATE_MIN_CHARS) &&
                isTextFieldFilled(state.carBrand.stateText, CAR_BRAND_MIN_CHARS) &&
                isTextFieldFilled(state.carCategory.stateText, CAR_INFO_MIN_CHARS) &&
                isTextFieldFilled(state.carLoadCapacity.stateText, CAR_INFO_MIN_CHARS) &&
                isTextFieldFilled(state.carCapacity.stateText, CAR_INFO_MIN_CHARS) &&
                !state.licencePlate.isValidationError && !state.carCategory.isValidationError &&
                !state.carBrand.isValidationError && !state.carLoadCapacity.isValidationError &&
                isTransportChanged(state)

    private fun isTransportChanged(state: TransportProfileState) =
        parameters.profileModel != parameters.profileModel.copy(
            licencePlate = state.licencePlate.stateText,
            carModel = state.carBrand.stateText,
            carCapacity = state.carCapacity.stateText.toInt(),
            carCategory = state.carCategory.stateText,
            carLoadCapacity = state.carLoadCapacity.stateText.toDoubleOrNull()
        )
}