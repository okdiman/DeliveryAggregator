package root.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import neworder.payment.presentation.PaymentSuccessParameters
import presentation.DeeplinkParameters
import root.presentation.compose.view.OrderRequestsView
import root.presentation.viewmodel.OrdersViewModel
import root.presentation.viewmodel.model.OrdersAction
import root.presentation.viewmodel.model.OrdersEvent
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun OrderRequestsScreen(deeplinkParameters: DeeplinkParameters?) {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { OrdersViewModel(deeplinkParameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        OrderRequestsView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is OrdersAction.OpenCreationErrorScreen -> {
                rootController.findRootController().push(
                    NavigationTree.NewOrder.CreationError.name,
                    params = (action.value as OrdersAction.OpenCreationErrorScreen).parameters
                )
                viewModel.obtainEvent(OrdersEvent.ResetAction)
            }
            OrdersAction.OpenNewOrderScreen -> {
                rootController.findRootController().push(
                    NavigationTree.NewOrder.Creating.name
                )
                viewModel.obtainEvent(OrdersEvent.ResetAction)
            }
            is OrdersAction.OpenPaymentSuccess -> {
                rootController.findRootController().push(
                    NavigationTree.NewOrder.PaymentSuccess.name,
                    params = PaymentSuccessParameters((action.value as OrdersAction.OpenPaymentSuccess).price)
                )
                viewModel.obtainEvent(OrdersEvent.ResetAction)
            }
            OrdersAction.OpenNotificationsScreen -> {}
            else -> {}
        }
    }
}