package editing.presentation.viewmodel.model

sealed interface EditProfileEvent {
    data class OnNameChanged(val name: String) : EditProfileEvent
    data class OnSurnameChanged(val surname: String) : EditProfileEvent
    data class OnSecondNameChanged(val secondName: String) : EditProfileEvent
    data class OnEmailChanged(val email: String) : EditProfileEvent
    object OnBackClick : EditProfileEvent
    object OnDeleteAccClick : EditProfileEvent
    object OnSaveEditingClick : EditProfileEvent
    object ResetAction : EditProfileEvent
}