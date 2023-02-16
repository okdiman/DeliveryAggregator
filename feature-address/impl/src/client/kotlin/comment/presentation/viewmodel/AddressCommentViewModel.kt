package comment.presentation.viewmodel

import BaseViewModel
import comment.presentation.compose.model.AddressCommentParamState
import comment.presentation.viewmodel.model.AddressCommentAction
import comment.presentation.viewmodel.model.AddressCommentEvent
import comment.presentation.viewmodel.model.AddressCommentState

class AddressCommentViewModel : BaseViewModel<AddressCommentState, AddressCommentAction, AddressCommentEvent>(
    initialState = AddressCommentState()
) {
    override fun obtainEvent(viewEvent: AddressCommentEvent) {
        when (viewEvent) {
            is AddressCommentEvent.OnCommentChanged -> onCommentChanged(viewEvent.text)
            AddressCommentEvent.OnBackClick -> onBackClick()
            AddressCommentEvent.OnDoneClick -> onDoneClick()
        }
    }

    private fun onCommentChanged(text: String) {
        viewState = viewState.copy(comment = AddressCommentParamState(stateText = text))
    }

    private fun onBackClick() {
        viewAction = AddressCommentAction.OpenPreviousScreen
    }

    private fun onDoneClick() {
        viewAction = AddressCommentAction.OpenAddressesScreen
    }
}