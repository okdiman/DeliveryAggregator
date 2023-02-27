package neworder.storage.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import neworder.storage.presentation.compose.view.NewOrderStorageView
import neworder.storage.presentation.viewmodel.NewOrderStorageViewModel
import neworder.storage.presentation.viewmodel.model.NewOrderStorageAction
import neworder.storage.presentation.viewmodel.model.NewOrderStorageEvent
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun NewOrderStorageScreen(parameters: NewOrderStorageParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { NewOrderStorageViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        NewOrderStorageView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is NewOrderStorageAction.UpdateNewOrderScreen -> {
                parameters.onStorageClick((action.value as NewOrderStorageAction.UpdateNewOrderScreen).storage)
                viewModel.obtainEvent(NewOrderStorageEvent.ResetAction)
            }
            NewOrderStorageAction.OpenPreviousScreen -> rootController.popBackStack()
            else -> {}
        }
    }
}