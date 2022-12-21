package user.presentation.viewmodel.model

sealed class UserEvent {
    data class OnNameChanged(val name: String) : UserEvent()
    data class OnSurnameChanged(val surname: String) : UserEvent()
    data class OnSecondNameChanged(val secondName: String) : UserEvent()
    data class OnEmailChanged(val email: String) : UserEvent()
    object OnBackClick : UserEvent()
    object OnCreateAccClick : UserEvent()
    object ResetAction : UserEvent()
}