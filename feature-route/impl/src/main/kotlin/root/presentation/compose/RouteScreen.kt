package root.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import root.presentation.viewmodel.RouteViewModel
import root.presentation.viewmodel.model.RouteAction
import root.presentation.viewmodel.model.RouteEvent
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun RouteScreen() {
    val rootController = LocalRootController.current
    StoredViewModel({ RouteViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        RouteView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is RouteAction.OpenRouteDetail -> {

                viewModel.obtainEvent(RouteEvent.ResetAction)
            }
            RouteAction.OpenNotifications -> {
                rootController.findRootController().push(
                    screen = NavigationTree.Routes.Notifications.name
                )
                viewModel.obtainEvent(RouteEvent.ResetAction)
            }
            else -> {}
        }
    }
}