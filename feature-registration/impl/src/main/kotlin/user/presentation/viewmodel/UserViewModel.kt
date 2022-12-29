package user.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import domain.usecase.SignUpUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.parameters.UserParameters
import user.presentation.mapper.SignUpModelMapper
import user.presentation.viewmodel.model.UserAction
import user.presentation.viewmodel.model.UserEvent
import user.presentation.viewmodel.model.UserState
import utils.CommonConstants.LIMITS.Common.MIN_NAME_CHARS
import utils.isEmailCorrect
import utils.isTextFieldFilled

class UserViewModel(
    private val parameters: UserParameters
) : BaseViewModel<UserState, UserAction, UserEvent>(
    initialState = UserState()
), KoinComponent {
    private val appDispatchers by inject<AppDispatchers>()
    private val signUp by inject<SignUpUseCase>()
    private val mapper by inject<SignUpModelMapper>()

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
        viewState = viewState.copy(
            name = viewState.name.copy(
                text = newName,
                isNameError = !isTextFieldFilled(newName, MIN_NAME_CHARS)
            ),
            isCreateAccButtonEnabled = isCreateButtonEnabled(name = newName)
        )
    }

    private fun onSurnameChanged(newSurname: String) {
        viewState = viewState.copy(
            surname = viewState.surname.copy(
                text = newSurname,
                isSurnameError = !isTextFieldFilled(newSurname, MIN_NAME_CHARS)
            ),
            isCreateAccButtonEnabled = isCreateButtonEnabled(surname = newSurname)
        )
    }

    private fun onSecondNameChanged(newSecondName: String) {
        viewState = viewState.copy(
            secondName = viewState.secondName.copy(
                text = newSecondName,
                isSecondNameError = !isTextFieldFilled(newSecondName, MIN_NAME_CHARS)
            ),
            isCreateAccButtonEnabled = isCreateButtonEnabled(name = newSecondName)
        )
    }

    private fun onEmailChanged(newEmail: String) {
        viewState = viewState.copy(
            email = viewState.email.copy(
                text = newEmail,
                isEmailError = !isEmailCorrect(newEmail)
            ),
            isCreateAccButtonEnabled = isCreateButtonEnabled(name = newEmail)
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

    private fun isCreateButtonEnabled(
        name: String = viewState.name.text,
        surname: String = viewState.surname.text,
        secondName: String = viewState.secondName.text,
        email: String = viewState.email.text
    ) = isTextFieldFilled(name, MIN_NAME_CHARS) && isTextFieldFilled(surname, MIN_NAME_CHARS) &&
            isTextFieldFilled(secondName, MIN_NAME_CHARS) && isEmailCorrect(email)
}