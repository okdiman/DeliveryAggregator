package root.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import root.presentation.compose.view.OrderRequestsView
import root.presentation.viewmodel.OrdersViewModel
import root.presentation.viewmodel.model.OrdersAction
import root.presentation.viewmodel.model.OrdersEvent
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun OrderRequestsScreen() {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { OrdersViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        OrderRequestsView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            OrdersAction.OpenNewOrderScreen -> {
                rootController.findRootController().push(
                    NavigationTree.NewOrder.Creating.name
                )
                viewModel.obtainEvent(OrdersEvent.ResetAction)
            }
            OrdersAction.OpenNotificationsScreen -> {}
            else -> {}
        }
    }
}