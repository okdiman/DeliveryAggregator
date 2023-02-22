package root.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import orderdetails.root.presentation.OrderDetailsParameters
import presentation.DeeplinkParameters
import root.presentation.compose.view.RouteView
import root.presentation.viewmodel.RouteViewModel
import root.presentation.viewmodel.model.RouteAction
import root.presentation.viewmodel.model.RouteEvent
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun RouteScreen(deeplinkParameters: DeeplinkParameters?) {
    val rootController = LocalRootController.current
    StoredViewModel({ RouteViewModel(deeplinkParameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        RouteView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is RouteAction.OpenRouteDetail -> {
                rootController.findRootController().push(
                    screen = NavigationTree.Routes.RouteDetails.name,
                    params = OrderDetailsParameters(
                        (action.value as RouteAction.OpenRouteDetail).id,
                        (action.value as RouteAction.OpenRouteDetail).index,
                        (action.value as RouteAction.OpenRouteDetail).isNeedToUpdateAfterBack
                    )
                )
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