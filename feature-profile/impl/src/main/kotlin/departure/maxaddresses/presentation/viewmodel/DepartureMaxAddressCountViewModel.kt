package departure.maxaddresses.presentation.viewmodel

import com.adeo.kviewmodel.BaseSharedViewModel
import departure.maxaddresses.presentation.viewmodel.model.DepartureMaxAddressCountAction
import departure.maxaddresses.presentation.viewmodel.model.DepartureMaxAddressCountEvent
import departure.maxaddresses.presentation.viewmodel.model.DepartureMaxAddressCountState

class DepartureMaxAddressCountViewModel :
    BaseSharedViewModel<DepartureMaxAddressCountState, DepartureMaxAddressCountAction, DepartureMaxAddressCountEvent>(
        initialState = DepartureMaxAddressCountState()
    ) {
    override fun obtainEvent(viewEvent: DepartureMaxAddressCountEvent) {
        when (viewEvent) {
            DepartureMaxAddressCountEvent.OnBackClick ->
                viewAction = DepartureMaxAddressCountAction.OpenPreviousScreen
        }
    }
}