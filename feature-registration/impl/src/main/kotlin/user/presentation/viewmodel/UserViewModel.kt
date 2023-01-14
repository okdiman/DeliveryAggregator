package user.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import di.modules.EMAIL_VALIDATOR_QUALIFIER
import di.modules.LETTERS_VALIDATOR_QUALIFIER
import domain.usecase.SignUpUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import presentation.parameters.UserParameters
import user.presentation.mapper.SignUpModelMapper
import user.presentation.viewmodel.model.UserAction
import user.presentation.viewmodel.model.UserEvent
import user.presentation.viewmodel.model.UserState
import utils.CommonConstants.LIMITS.Common.MIN_NAME_CHARS
import utils.isTextFieldFilled
import utils.validators.domain.TextFieldValidator

class UserViewModel(
    private val parameters: UserParameters
) : BaseViewModel<UserState, UserAction, UserEvent>(initialState = UserState()), KoinComponent {
    private val appDispatchers by inject<AppDispatchers>()
    private val signUp by inject<SignUpUseCase>()
    private val mapper by inject<SignUpModelMapper>()
    private val lettersValidator by inject<TextFieldValidator>(named(LETTERS_VALIDATOR_QUALIFIER))
    private val emailValidator by inject<TextFieldValidator>(named(EMAIL_VALIDATOR_QUALIFIER))

    override fun obtainEvent(viewEvent: UserEvent) {
        when (viewEvent) {
            is UserEvent.OnNameChanged -> onNameChanged(viewEvent.name)
            is UserEvent.OnSurnameChanged -> onSurnameChanged(viewEvent.surname)
            is UserEvent.OnSecondNameChanged -> onSecondNameChanged(viewEvent.secondName)
            is UserEvent.OnEmailChanged -> onEmailChanged(viewEvent.email)
            UserEvent.OnBackClick -> onBackClick()
            UserEvent.OnCreateAccClick -> onCreateAccClick()
            UserEvent.ResetAction -> onResetAction()
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
            isCreateAccButtonEnabled = isCreateButtonEnabled(
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
            isCreateAccButtonEnabled = isCreateButtonEnabled(
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
            isCreateAccButtonEnabled = isCreateButtonEnabled(
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
            isCreateAccButtonEnabled = isCreateButtonEnabled(
                viewState.copy(email = viewState.email.copy(stateText = newEmail))
            )
        )
    }

    private fun onBackClick() {
        viewAction = UserAction.OpenPreviousStep
    }

    private fun onCreateAccClick() {
        launchJob(context = appDispatchers.network) {
            signUp(mapper.map(parameters, viewState))
            viewAction = UserAction.OpenMainFlow
        }
    }

    private fun isCreateButtonEnabled(state: UserState) =
        isTextFieldFilled(state.name.stateText, MIN_NAME_CHARS) &&
                isTextFieldFilled(state.surname.stateText, MIN_NAME_CHARS) &&
                isTextFieldFilled(state.secondName.stateText, MIN_NAME_CHARS) &&
                !state.name.isValidationError && !state.surname.isValidationError &&
                !state.secondName.isValidationError && emailValidator.isValidate(state.email.stateText)
}