package support.presentation.viewmodel

import BaseViewModel
import support.presentation.viewmodel.model.SupportAction
import support.presentation.viewmodel.model.SupportEvent
import support.presentation.viewmodel.model.SupportState

class SupportViewModel: BaseViewModel<SupportState, SupportAction, SupportEvent>(
    initialState = SupportState()
) {

    override fun obtainEvent(viewEvent: SupportEvent) {
        viewAction = when (viewEvent) {
            SupportEvent.OnBackClick -> SupportAction.OpenPreviousScreen
            SupportEvent.OnCallClick -> SupportAction.OpenDialIntent
            SupportEvent.OnEmailClick -> SupportAction.OpenSendEmailIntent
            SupportEvent.ResetAction -> null
        }
    }
}