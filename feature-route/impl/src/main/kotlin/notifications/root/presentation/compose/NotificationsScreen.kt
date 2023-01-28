package notifications.root.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import notifications.root.presentation.viewmodel.NotificationsViewModel
import notifications.root.presentation.viewmodel.model.NotificationsAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun NotificationsScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { NotificationsViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        NotificationsView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            NotificationsAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            else -> {}
        }
    }
}