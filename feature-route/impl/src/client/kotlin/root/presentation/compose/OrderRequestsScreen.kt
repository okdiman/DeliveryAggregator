package root.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import root.presentation.compose.view.OrderRequestsView
import root.presentation.viewmodel.OrderRequestsViewModel
import root.presentation.viewmodel.model.OrderRequestsAction

@Composable
fun OrderRequestsScreen() {
    StoredViewModel(factory = { OrderRequestsViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        OrderRequestsView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            OrderRequestsAction.OpenNewOrderScreen -> {}
            OrderRequestsAction.OpenNotificationsScreen -> {}
            else -> {}
        }
    }
}
