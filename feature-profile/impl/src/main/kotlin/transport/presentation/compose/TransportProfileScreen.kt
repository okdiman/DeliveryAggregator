package transport.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import navigation.NavigationTree
import presentation.TransportProfileParameters
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import transport.presentation.viewmodel.TransportProfileViewModel
import transport.presentation.viewmodel.model.TransportProfileAction
import utils.CommonConstants

@Composable
fun TransportProfileScreen(parameters: TransportProfileParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { TransportProfileViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        TransportProfileView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            TransportProfileAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            TransportProfileAction.OpenProfileWithUpdate -> {
                rootController.present(
                    screen = NavigationTree.Main.MainFlow.name,
                    launchFlag = LaunchFlag.SingleInstance,
                    startTabPosition = CommonConstants.BottomNavigation.PROFILE_TAB
                )
            }
            else -> {}
        }
    }
}