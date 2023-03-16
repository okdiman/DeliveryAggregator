package root.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import neworder.payment.presentation.PaymentSuccessParameters
import permissions.PermissionsConstants
import permissions.presentation.PermissionHandler
import presentation.DeeplinkParameters
import root.presentation.compose.view.OrderRequestsView
import root.presentation.viewmodel.OrdersViewModel
import root.presentation.viewmodel.model.OrdersAction
import root.presentation.viewmodel.model.OrdersEvent
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import trinity_monsters.delivery_aggregator.feature_route.impl.R

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
            OrdersAction.OpenNotificationsScreen -> {
                rootController.findRootController().push(
                    NavigationTree.Routes.Notifications.name
                )
                viewModel.obtainEvent(OrdersEvent.ResetAction)
            }
            else -> {}
        }
        PermissionHandler(
            permission = PermissionsConstants.Notification,
            title = stringResource(id = R.string.order_notifications_permission),
            state = state.value,
            onPermissionStateChanged = {
                viewModel.obtainEvent(OrdersEvent.OnPermissionStateChanged(it))
            },
            onRationaleDismiss = {
                viewModel.obtainEvent(OrdersEvent.OnRationaleDismiss)
            }
        )
    }
}
