package presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import navigation.NavigationTree
import presentation.viewmodel.SplashViewModel
import presentation.viewmodel.model.SplashAction
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag

@Composable
fun SplashScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { SplashViewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        SplashView()
        when (action.value) {
            SplashAction.OpenMainFlow -> {
                rootController.present(
                    screen = NavigationTree.Main.MainFlow.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
            }
            SplashAction.OpenAuthorizationFlow -> {
                rootController.present(
                    screen = NavigationTree.Auth.AuthFlow.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
            }
            else -> {}
        }
    }
}