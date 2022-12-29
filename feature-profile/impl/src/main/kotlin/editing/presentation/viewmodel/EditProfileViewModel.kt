package editing.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import domain.GetMaskedPhoneUseCase
import editing.domain.UpdateProfileUseCase
import editing.presentation.viewmodel.model.EditProfileAction
import editing.presentation.viewmodel.model.EditProfileEvent
import editing.presentation.viewmodel.model.EditProfileState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.EditProfileParameters
import utils.CommonConstants.LIMITS.Common.MIN_NAME_CHARS
import utils.isEmailCorrect
import utils.isTextFieldFilled

class EditProfileViewModel(
    private val parameters: EditProfileParameters
) : BaseViewModel<EditProfileState, EditProfileAction, EditProfileEvent>(
    initialState = EditProfileState()
), KoinComponent {
    private val appDispatchers by inject<AppDispatchers>()
    private val updateProfile by inject<UpdateProfileUseCase>()
    private val getMaskedPhone by inject<GetMaskedPhoneUseCase>()

    init {
        viewState = viewState.copy(
            name = viewState.name.copy(text = parameters.profileModel.name),
            secondName = viewState.secondName.copy(text = parameters.profileModel.secondName),
            surname = viewState.surname.copy(text = parameters.profileModel.surname),
            email = viewState.email.copy(text = parameters.profileModel.email),
            phone = viewState.phone.copy(text = getMaskedPhone(parameters.profileModel.phone)),
            organizationName = viewState.organizationName.copy(text = parameters.profileModel.organizationName)
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
        viewState = viewState.copy(
            name = viewState.name.copy(
                text = newName,
                isNameError = !isTextFieldFilled(newName, MIN_NAME_CHARS)
            ),
            isSaveButtonVisible = isSaveButtonVisible(newName = newName, isUpdated = false)
        )
    }

    private fun onSurnameChanged(newSurname: String) {
        viewState = viewState.copy(
            surname = viewState.surname.copy(
                text = newSurname,
                isSurnameError = !isTextFieldFilled(newSurname, MIN_NAME_CHARS)
            ),
            isSaveButtonVisible = isSaveButtonVisible(newSurname = newSurname, isUpdated = false)
        )
    }

    private fun onSecondNameChanged(newSecondName: String) {
        viewState = viewState.copy(
            secondName = viewState.secondName.copy(
                text = newSecondName,
                isSecondNameError = !isTextFieldFilled(newSecondName, MIN_NAME_CHARS)
            ),
            isSaveButtonVisible = isSaveButtonVisible(
                newSecondName = newSecondName,
                isUpdated = false
            )
        )
    }

    private fun onEmailChanged(newEmail: String) {
        viewState = viewState.copy(
            email = viewState.email.copy(
                text = newEmail,
                isEmailError = !isEmailCorrect(newEmail)
            ),
            isSaveButtonVisible = isSaveButtonVisible(newEmail = newEmail, isUpdated = false)
        )
    }

    private fun onBackClick() {
        viewAction = EditProfileAction.OpenPreviousScreen
    }

    private fun onSaveEditing() {
        launchJob(appDispatchers.network) {
            updateProfile(
                parameters.profileModel.copy(
                    name = viewState.name.text,
                    secondName = viewState.secondName.text,
                    surname = viewState.surname.text,
                    email = viewState.email.text
                )
            )
            viewState = viewState.copy(isSaveButtonVisible = isSaveButtonVisible(isUpdated = true))
            viewAction = EditProfileAction.ShowProfileUpdatedSnackbar
        }
    }

    private fun onDeleteAcc() {
        viewAction = EditProfileAction.OpenDeleteAccScreen
    }

    private fun isSaveButtonVisible(
        newName: String = viewState.name.text,
        newSurname: String = viewState.surname.text,
        newSecondName: String = viewState.secondName.text,
        newEmail: String = viewState.email.text,
        isUpdated: Boolean = false
    ): Boolean {
        return !isUpdated && isTextFieldFilled(newName, MIN_NAME_CHARS) &&
                isTextFieldFilled(newName, MIN_NAME_CHARS) &&
                isTextFieldFilled(newName, MIN_NAME_CHARS) && isEmailCorrect(newEmail) &&
                parameters.profileModel != parameters.profileModel.copy(
            email = newEmail, name = newName, surname = newSurname, secondName = newSecondName
        )
    }
}