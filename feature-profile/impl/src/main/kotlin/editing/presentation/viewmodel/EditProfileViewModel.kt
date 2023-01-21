package editing.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import di.modules.EMAIL_VALIDATOR_QUALIFIER
import di.modules.LETTERS_VALIDATOR_QUALIFIER
import domain.usecase.GetMaskedPhoneUseCase
import editing.presentation.viewmodel.model.EditProfileAction
import editing.presentation.viewmodel.model.EditProfileEvent
import editing.presentation.viewmodel.model.EditProfileState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import editing.presentation.EditProfileParameters
import root.domain.UpdateProfileUseCase
import utils.CommonConstants.LIMITS.Common.MIN_NAME_CHARS
import utils.isTextFieldFilled
import utils.validators.domain.TextFieldValidator

class EditProfileViewModel(
    private val parameters: EditProfileParameters
) : BaseViewModel<EditProfileState, EditProfileAction, EditProfileEvent>(
    initialState = EditProfileState()
), KoinComponent {
    private val appDispatchers by inject<AppDispatchers>()
    private val updateProfile by inject<UpdateProfileUseCase>()
    private val getMaskedPhone by inject<GetMaskedPhoneUseCase>()
    private val emailValidator by inject<TextFieldValidator>(named(EMAIL_VALIDATOR_QUALIFIER))
    private val lettersValidator by inject<TextFieldValidator>(named(LETTERS_VALIDATOR_QUALIFIER))

    init {
        viewState = viewState.copy(
            name = viewState.name.copy(stateText = parameters.profileModel.name),
            secondName = viewState.secondName.copy(stateText = parameters.profileModel.secondName),
            surname = viewState.surname.copy(stateText = parameters.profileModel.surname),
            email = viewState.email.copy(stateText = parameters.profileModel.email),
            phone = viewState.phone.copy(stateText = getMaskedPhone(parameters.profileModel.phone)),
            organizationName = viewState.organizationName.copy(stateText = parameters.profileModel.organizationName)
        )
    }

    override fun obtainEvent(viewEvent: EditProfileEvent) {
        when (viewEvent) {
            is EditProfileEvent.OnNameChanged -> onNameChanged(viewEvent.name)
            is EditProfileEvent.OnSurnameChanged -> onSurnameChanged(viewEvent.surname)
            is EditProfileEvent.OnSecondNameChanged -> onSecondNameChanged(viewEvent.secondName)
            is EditProfileEvent.OnEmailChanged -> onEmailChanged(viewEvent.email)
            EditProfileEvent.OnBackClick -> onBackClick()
            EditProfileEvent.OnSaveEditingClick -> onSaveEditing()
            EditProfileEvent.OnDeleteAccClick -> onDeleteAcc()
            EditProfileEvent.ResetAction -> onResetAction()
        }
    }

    private fun onNameChanged(newName: String) {
        val isValid = lettersValidator.isValidate(newName)
        viewState = viewState.copy(
            name = viewState.name.copy(
                stateText = newName,
                isFillingError = !isTextFieldFilled(newName, MIN_NAME_CHARS),
                isValidationError = !isValid
            ),
            isSaveButtonVisible = isSaveButtonVisible(
                viewState.copy(
                    name = viewState.name.copy(
                        stateText = newName,
                        isValidationError = !isValid
                    )
                )
            )
        )
    }

    private fun onSurnameChanged(newSurname: String) {
        val isValid = lettersValidator.isValidate(newSurname)
        viewState = viewState.copy(
            surname = viewState.surname.copy(
                stateText = newSurname,
                isFillingError = !isTextFieldFilled(newSurname, MIN_NAME_CHARS),
                isValidationError = !isValid
            ),
            isSaveButtonVisible = isSaveButtonVisible(
                viewState.copy(
                    surname = viewState.surname.copy(
                        stateText = newSurname,
                        isValidationError = !isValid
                    )
                )
            )
        )
    }

    private fun onSecondNameChanged(newSecondName: String) {
        val isValid = lettersValidator.isValidate(newSecondName)
        viewState = viewState.copy(
            secondName = viewState.secondName.copy(
                stateText = newSecondName,
                isFillingError = !isTextFieldFilled(newSecondName, MIN_NAME_CHARS),
                isValidationError = !isValid
            ),
            isSaveButtonVisible = isSaveButtonVisible(
                viewState.copy(
                    secondName = viewState.secondName.copy(
                        stateText = newSecondName,
                        isValidationError = !isValid
                    )
                )
            )
        )
    }

    private fun onEmailChanged(newEmail: String) {
        viewState = viewState.copy(
            email = viewState.email.copy(
                stateText = newEmail,
                isValidationError = !emailValidator.isValidate(newEmail)
            ),
            isSaveButtonVisible = isSaveButtonVisible(
                viewState.copy(email = viewState.email.copy(stateText = newEmail))
            )
        )
    }

    private fun onBackClick() {
        viewAction = EditProfileAction.OpenPreviousScreen
    }

    private fun onSaveEditing() {
        launchJob(appDispatchers.network) {
            updateProfile(
                parameters.profileModel.copy(
                    name = viewState.name.stateText,
                    secondName = viewState.secondName.stateText,
                    surname = viewState.surname.stateText,
                    email = viewState.email.stateText
                )
            )
            viewAction = EditProfileAction.OpenProfileScreenWithUpdate
        }
    }

    private fun onDeleteAcc() {
        viewAction = EditProfileAction.OpenDeleteAccScreen
    }

    private fun isSaveButtonVisible(state: EditProfileState) =
        isTextFieldFilled(state.name.stateText, MIN_NAME_CHARS) &&
                isTextFieldFilled(state.surname.stateText, MIN_NAME_CHARS) &&
                isTextFieldFilled(state.secondName.stateText, MIN_NAME_CHARS) &&
                emailValidator.isValidate(state.email.stateText) &&
                !state.name.isValidationError && !state.surname.isValidationError &&
                !state.secondName.isValidationError &&
                parameters.profileModel != parameters.profileModel.copy(
            email = state.email.stateText,
            name = state.name.stateText,
            surname = state.surname.stateText,
            secondName = state.secondName.stateText
        )
}