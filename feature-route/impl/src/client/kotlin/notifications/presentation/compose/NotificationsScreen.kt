package notifications.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import notifications.presentation.compose.view.NotificationsView
import notifications.presentation.viewmodel.NotificationsViewModel
import notifications.presentation.viewmodel.model.NotificationsAction
import notifications.presentation.viewmodel.model.NotificationsEvent
import orderchanges.presentation.ConfirmOrderChangesParameters
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
            else -> {}
        }
    }
}
