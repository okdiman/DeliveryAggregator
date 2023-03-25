package deleteorder.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import deleteorder.domain.DeleteOrderUseCase
import deleteorder.presentation.DeleteOrderParameters
import deleteorder.presentation.viewmodel.model.DeleteOrderAction
import deleteorder.presentation.viewmodel.model.DeleteOrderEvent
import deleteorder.presentation.viewmodel.model.DeleteOrderState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteOrderViewModel(
    private val parameters: DeleteOrderParameters
) : BaseViewModel<DeleteOrderState, DeleteOrderAction, DeleteOrderEvent>(
    initialState = DeleteOrderState()
), KoinComponent {

    private val deleteOrder by inject<DeleteOrderUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    override fun obtainEvent(viewEvent: DeleteOrderEvent) {
        when (viewEvent) {
            DeleteOrderEvent.OnCancelClick -> onCancelClick()
            DeleteOrderEvent.OnConfirmClick -> onConfirmClick()
        }
    }

    private fun onCancelClick() {
        viewAction = DeleteOrderAction.CloseScreen
    }

    private fun onConfirmClick() {
        launchJob(appDispatchers.network) {
            deleteOrder(parameters.id)
            viewAction = DeleteOrderAction.OpenMainScreen
        }
    }
}