package orderdetails.loadingstate.presentation.viewmodel.model

sealed interface OrderLoadingAction {
    object OpenCargoTypeScreen : OrderLoadingAction
    object OpenAdditionalOptionsScreen : OrderLoadingAction
    object OpenPreviousScreen : OrderLoadingAction
    object OpenCamera : OrderLoadingAction
}