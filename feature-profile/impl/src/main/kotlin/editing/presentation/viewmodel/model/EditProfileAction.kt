package editing.presentation.viewmodel.model

sealed class EditProfileAction {
    object OpenPreviousScreen : EditProfileAction()
    object OpenDeleteAccScreen : EditProfileAction()
    object ShowProfileUpdatedSnackbar : EditProfileAction()
}