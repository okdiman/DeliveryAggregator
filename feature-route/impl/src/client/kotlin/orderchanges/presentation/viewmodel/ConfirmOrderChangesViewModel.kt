package orderchanges.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import orderchanges.domain.usecase.ConfirmOrderChangesUseCase
import orderchanges.domain.usecase.GetOrderChangesUseCase
import orderchanges.presentation.ConfirmOrderChangesParameters
import orderchanges.presentation.mapper.OrderChangesUiMapper
import orderchanges.presentation.viewmodel.model.ConfirmOrderChangesAction
import orderchanges.presentation.viewmodel.model.ConfirmOrderChangesEvent
import orderchanges.presentation.viewmodel.model.ConfirmOrderChangesState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ConfirmOrderChangesViewModel(
    private val parameters: ConfirmOrderChangesParameters,
) : BaseViewModel<ConfirmOrderChangesState, ConfirmOrderChangesAction, ConfirmOrderChangesEvent>(
    initialState = ConfirmOrderChangesState()
), KoinComponent {

    private val appDispatchers by inject<AppDispatchers>()
    private val getOrderChanges by inject<GetOrderChangesUseCase>()
    private val confirmOrderChanges by inject<ConfirmOrderChangesUseCase>()
    private val orderChangesUiMapper by inject<OrderChangesUiMapper>()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: ConfirmOrderChangesEvent) {
        when (viewEvent) {
            ConfirmOrderChangesEvent.OnBackClick -> onBackClick()
            ConfirmOrderChangesEvent.OnConfirmClick -> onConfirmChanges()
            ConfirmOrderChangesEvent.OnRetryClick -> getContent()
            ConfirmOrderChangesEvent.ResetAction -> onResetAction()
        }
    }

    private fun getContent() {
        launchJob(context = appDispatchers.network) {
            viewState = viewState.copy(isError = false, isLoading = true)
            val orderChanges = getOrderChanges(parameters.id)
            viewState = viewState.copy(
                isLoading = false,
                isRefreshing = false,
                changes = orderChangesUiMapper.map(orderChanges),
                orderId = orderChanges.orderId
            )
        }
    }

    private fun onConfirmChanges() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isLoading = false, isError = true)
        }) {
            viewState = viewState.copy(isConfirming = true, isError = false)
            confirmOrderChanges(parameters.id)
            viewAction = ConfirmOrderChangesAction.OpenPreviousScreen
        }
    }

    private fun onBackClick() {
        viewAction = ConfirmOrderChangesAction.OpenPreviousScreen
    }
}
