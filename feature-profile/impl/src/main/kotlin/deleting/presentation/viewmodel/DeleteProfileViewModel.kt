package deleting.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import deleting.presentation.viewmodel.model.DeleteProfileAction
import deleting.presentation.viewmodel.model.DeleteProfileEvent
import deleting.presentation.viewmodel.model.DeleteProfileState
import exit.domain.ExitFromProfileUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteProfileViewModel :
    BaseViewModel<DeleteProfileState, DeleteProfileAction, DeleteProfileEvent>(
        initialState = DeleteProfileState()
    ), KoinComponent {
    private val exitFromProfile by inject<ExitFromProfileUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    override fun obtainEvent(viewEvent: DeleteProfileEvent) {
        when (viewEvent) {
            DeleteProfileEvent.OnDeleteClick -> onDeleteClick()
            DeleteProfileEvent.OnBackClick -> onBackClick()
        }
    }

    private fun onDeleteClick() {
        launchJob(context = appDispatchers.network) {
            exitFromProfile(isDeleting = true)
            viewAction = DeleteProfileAction.OpenLoginFlow
        }
    }

    private fun onBackClick() {
        viewAction = DeleteProfileAction.OpenPreviousScreen
    }
}