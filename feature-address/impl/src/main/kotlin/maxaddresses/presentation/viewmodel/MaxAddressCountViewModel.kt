package maxaddresses.presentation.viewmodel

import BaseViewModel
import maxaddresses.presentation.viewmodel.model.MaxAddressCountAction
import maxaddresses.presentation.viewmodel.model.MaxAddressCountEvent
import maxaddresses.presentation.viewmodel.model.MaxAddressCountState

class MaxAddressCountViewModel :
    BaseViewModel<MaxAddressCountState, MaxAddressCountAction, MaxAddressCountEvent>(
        initialState = MaxAddressCountState()
    ) {
    override fun obtainEvent(viewEvent: MaxAddressCountEvent) {
        when (viewEvent) {
            MaxAddressCountEvent.OnBackClick -> viewAction = MaxAddressCountAction.OpenPreviousScreen
        }
    }
}