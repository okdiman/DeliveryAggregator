package departure.presentation.viewmodel.model

sealed interface DepartureAction {
    object OpenPreviousScreen : DepartureAction
    object OpenAddressEdit : DepartureAction
    object OpenAddAddress : DepartureAction
    object OpenAddingError : DepartureAction
}