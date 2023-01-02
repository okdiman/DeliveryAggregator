package editing.presentation.viewmodel.model

sealed interface EditProfileAction {
    object OpenPreviousScreen : EditProfileAction
    object OpenDeleteAccScreen : EditProfileAction
    object OpenProfileScreenWithUpdate : EditProfileAction
}