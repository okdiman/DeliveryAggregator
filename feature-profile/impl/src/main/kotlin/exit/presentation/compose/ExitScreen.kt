package exit.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import exit.presentation.viewmodel.ExitViewModel
import exit.presentation.viewmodel.model.ExitAction
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag

@Composable
fun ExitScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { ExitViewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        ExitView { viewModel.obtainEvent(it) }
        when (action.value) {
            ExitAction.OpenLoginFlow -> {
                rootController.findRootController().present(
                    screen = NavigationTree.Auth.AuthFlow.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
            }
            ExitAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            else -> {}
        }
    }
}