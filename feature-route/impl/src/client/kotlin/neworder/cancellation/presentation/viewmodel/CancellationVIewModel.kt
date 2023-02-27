package neworder.cancellation.presentation.viewmodel

import BaseViewModel
import neworder.cancellation.presentation.viewmodel.model.CancellationAction
import neworder.cancellation.presentation.viewmodel.model.CancellationEvent
import neworder.cancellation.presentation.viewmodel.model.CancellationState

class CancellationVIewModel : BaseViewModel<CancellationState, CancellationAction, CancellationEvent>(
    initialState = CancellationState()
) {
    override fun obtainEvent(viewEvent: CancellationEvent) {
        viewAction = when (viewEvent) {
            CancellationEvent.OnCancelClick -> CancellationAction.OpenStartScreen
            CancellationEvent.OnNotCancelClick -> CancellationAction.OpenNewOrderScreen
        }
    }
}