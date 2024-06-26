package neworder.cancellation.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import navigation.BottomNavConstants
import navigation.NavigationTree
import neworder.cancellation.presentation.viewmodel.CancellationVIewModel
import neworder.cancellation.presentation.viewmodel.model.CancellationAction
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag

@Composable
internal fun CancellationOrderScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { CancellationVIewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        CancellationOrderView { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            CancellationAction.OpenNewOrderScreen -> {
                rootController.findModalController().popBackStack(null)
            }
            CancellationAction.OpenStartScreen -> {
                rootController.findRootController().present(
                    screen = NavigationTree.Main.MainFlow.name,
                    launchFlag = LaunchFlag.SingleNewTask,
                    startTabPosition = BottomNavConstants.ROUTE_TAB
                )
            }
            else -> {}
        }
    }
}