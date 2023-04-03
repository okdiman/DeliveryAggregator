package deleteorder.presentation.viewmodel.model

sealed interface DeleteOrderEvent {
    object OnCancelClick : DeleteOrderEvent
    object OnConfirmClick : DeleteOrderEvent
}