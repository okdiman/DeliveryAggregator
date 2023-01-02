package exit.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import exit.domain.ExitFromProfileUseCase
import exit.presentation.viewmodel.model.ExitAction
import exit.presentation.viewmodel.model.ExitEvent
import exit.presentation.viewmodel.model.ExitState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ExitViewModel : BaseViewModel<ExitState, ExitAction, ExitEvent>(
    initialState = ExitState()
), KoinComponent {
    private val appDispatchers by inject<AppDispatchers>()
    private val exitFromProfile by inject<ExitFromProfileUseCase>()

    override fun obtainEvent(viewEvent: ExitEvent) {
        when (viewEvent) {
            ExitEvent.OnConfirmClick -> onConfirmCLick()
            ExitEvent.OnStayClick -> onStayClick()
        }
    }

    private fun onConfirmCLick() {
        launchJob(appDispatchers.storage) {
            exitFromProfile()
            viewAction = ExitAction.OpenLoginFlow
        }
    }

    private fun onStayClick() {
        viewAction = ExitAction.OpenPreviousScreen
    }
}