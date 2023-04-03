package deleteorder.presentation.viewmodel.model

sealed interface DeleteOrderAction {
    object CloseScreen : DeleteOrderAction
    object OpenMainScreen : DeleteOrderAction
}