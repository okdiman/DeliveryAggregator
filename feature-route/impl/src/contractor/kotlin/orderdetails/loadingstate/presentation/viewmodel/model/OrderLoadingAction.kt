package orderdetails.loadingstate.presentation.viewmodel.model

sealed interface OrderLoadingAction {
    object OpenCargoTypeScreen : OrderLoadingAction
    object OpenExtrasScreen : OrderLoadingAction
    object OpenPreviousScreen : OrderLoadingAction
    object OpenCamera : OrderLoadingAction
}