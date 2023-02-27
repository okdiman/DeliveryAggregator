package neworder.storage.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import neworder.storage.domain.GetStoragesUseCase
import neworder.storage.presentation.compose.NewOrderStorageParameters
import neworder.storage.presentation.viewmodel.model.NewOrderStorageAction
import neworder.storage.presentation.viewmodel.model.NewOrderStorageEvent
import neworder.storage.presentation.viewmodel.model.NewOrderStorageState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.presentation.compose.model.RouteStorageModel

class NewOrderStorageViewModel(
    private val parameters: NewOrderStorageParameters
) : BaseViewModel<NewOrderStorageState, NewOrderStorageAction, NewOrderStorageEvent>(
    initialState = NewOrderStorageState()
), KoinComponent {
    private val appDispatchers by inject<AppDispatchers>()
    private val getStorages by inject<GetStoragesUseCase>()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: NewOrderStorageEvent) {
        when (viewEvent) {
            is NewOrderStorageEvent.OnStorageClick -> onStorageClick(viewEvent.storage)
            NewOrderStorageEvent.OnBackClick -> onBackClick()
            NewOrderStorageEvent.OnRetryClick -> getContent()
            NewOrderStorageEvent.ResetAction -> onResetAction()
        }
    }

    private fun onStorageClick(storage: RouteStorageModel) {
        viewState = viewState.copy(activeStorage = storage.name)
        viewAction = NewOrderStorageAction.UpdateNewOrderScreen(storage)
    }

    private fun onBackClick() {
        viewAction = NewOrderStorageAction.OpenPreviousScreen
    }

    private fun getContent() {
        launchJob(
            context = appDispatchers.network,
            onError = {
                viewState = viewState.copy(isLoading = false, isError = true)
            }
        ) {
            viewState = viewState.copy(isLoading = true, isError = false)
            viewState = viewState.copy(
                isLoading = false,
                isError = false,
                storages = getStorages(),
                activeStorage = parameters.paramState.stateText
            )
        }
    }
}