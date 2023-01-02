package exit.presentation.viewmodel.model

sealed interface ExitAction {
    object OpenLoginFlow : ExitAction
    object OpenPreviousScreen : ExitAction
}