package notifications.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import notifications.presentation.compose.view.NotificationsView
import notifications.presentation.viewmodel.NotificationsViewModel
import notifications.presentation.viewmodel.model.NotificationsAction
import notifications.presentation.viewmodel.model.NotificationsEvent
import openBrowser
import orderchanges.presentation.ConfirmOrderChangesParameters
import orderdetails.root.presentation.OrderDetailsParameters
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun NotificationsScreen() {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { NotificationsViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        NotificationsView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            NotificationsAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            is NotificationsAction.OpenConfirmChangesScreen -> {
                rootController.push(
                    screen = NavigationTree.Routes.ConfirmChanges.name,
                    params = ConfirmOrderChangesParameters(
                        (action.value as NotificationsAction.OpenConfirmChangesScreen).orderId
                    )
                )
                viewModel.obtainEvent(NotificationsEvent.ResetAction)
            }
            is NotificationsAction.OpenOrderDetails -> {
                rootController.push(
                    screen = NavigationTree.Routes.Details.name,
                    params = OrderDetailsParameters(
                        (action.value as NotificationsAction.OpenOrderDetails).orderId
                    )
                )
                viewModel.obtainEvent(NotificationsEvent.ResetAction)
            }
            is NotificationsAction.OpenPaymentInBrowser -> {
                val uri = (action.value as NotificationsAction.OpenPaymentInBrowser).paymentUri
                openBrowser(LocalContext.current, uri)
            }
            else -> {}
        }
    }
}