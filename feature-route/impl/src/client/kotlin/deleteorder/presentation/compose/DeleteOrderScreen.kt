package deleteorder.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import deleteorder.presentation.DeleteOrderParameters
import deleteorder.presentation.viewmodel.DeleteOrderViewModel
import deleteorder.presentation.viewmodel.model.DeleteOrderAction
import navigation.BottomNavConstants
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag

@Composable
internal fun DeleteOrderScreen(parameters: DeleteOrderParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { DeleteOrderViewModel(parameters) }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        DeleteOrderView { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            DeleteOrderAction.CloseScreen -> rootController.findRootController().popBackStack()
            DeleteOrderAction.OpenMainScreen -> {
                rootController.present(
                    screen = NavigationTree.Main.MainFlow.name,
                    launchFlag = LaunchFlag.SingleInstance,
                    startTabPosition = BottomNavConstants.ROUTE_TAB
                )
            }
            else -> {}
        }
    }
}