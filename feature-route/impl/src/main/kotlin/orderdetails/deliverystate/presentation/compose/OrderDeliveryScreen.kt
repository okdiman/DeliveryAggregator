package orderdetails.deliverystate.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import orderdetails.deliverystate.presentation.viewmodel.OrderDeliveryViewModel
import orderdetails.deliverystate.presentation.viewmodel.model.OrderDeliveryAction
import orderdetails.deliverystate.presentation.viewmodel.model.OrderDeliveryEvent
import orderdetails.root.presentation.OrderStatesParameters
import permissions.PermissionsConstants
import permissions.presentation.PermissionHandler
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
fun OrderDeliveryScreen(parameters: OrderStatesParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { OrderDeliveryViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        OrderDeliveryView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            OrderDeliveryAction.OpenCamera -> {
                PermissionHandler(
                    permission = PermissionsConstants.Camera,
                    title = stringResource(id = R.string.loading_camera_permission),
                    state = state.value,
                    onPermissionStateChanged = {
                        viewModel.obtainEvent(OrderDeliveryEvent.OnPermissionStateChanged(it))
                    },
                    onRationaleDismiss = {
                        viewModel.obtainEvent(OrderDeliveryEvent.OnRationaleDismiss)
                    }
                )
                viewModel.obtainEvent(OrderDeliveryEvent.ResetAction)
            }
            OrderDeliveryAction.OpenPreviousScreen -> rootController.popBackStack()
            else -> {}
        }
    }
}