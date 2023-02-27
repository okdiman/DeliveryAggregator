package departure.presentation.viewmodel.model

sealed interface DepartureAction {
    data class OpenAddressEdit(val id: String) : DepartureAction
    object OpenPreviousScreen : DepartureAction
    object OpenAddAddress : DepartureAction
    object OpenAddingError : DepartureAction
}