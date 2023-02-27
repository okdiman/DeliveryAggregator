package neworder.cancellation.presentation.viewmodel.model

sealed interface CancellationAction {
    object OpenStartScreen : CancellationAction
    object OpenNewOrderScreen : CancellationAction
}