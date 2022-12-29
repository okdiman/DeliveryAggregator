package deleting.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import deleting.domain.DeleteProfileUseCase
import deleting.presentation.viewmodel.model.DeleteProfileAction
import deleting.presentation.viewmodel.model.DeleteProfileEvent
import deleting.presentation.viewmodel.model.DeleteProfileState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteProfileViewModel :
    BaseViewModel<DeleteProfileState, DeleteProfileAction, DeleteProfileEvent>(
        initialState = DeleteProfileState()
    ), KoinComponent {
    private val deleteProfile by inject<DeleteProfileUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    override fun obtainEvent(viewEvent: DeleteProfileEvent) {
        when (viewEvent) {
            DeleteProfileEvent.OnDeleteClick -> onDeleteClick()
            DeleteProfileEvent.OnBackClick -> onBackClick()
        }
    }

    private fun onDeleteClick() {
        launchJob(context = appDispatchers.network) {
            deleteProfile()
            viewAction = DeleteProfileAction.OpenLoginFlow
        }
    }

    private fun onBackClick() {
        viewAction = DeleteProfileAction.OpenPreviousScreen
    }
}