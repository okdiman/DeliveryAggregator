package deleting.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import deleting.presentation.viewmodel.DeleteProfileViewModel
import deleting.presentation.viewmodel.model.DeleteProfileAction
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag

@Composable
fun DeleteProfileScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { DeleteProfileViewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        DeleteProfileView { viewModel.obtainEvent(it) }
        when (action.value) {
            is DeleteProfileAction.OpenLoginFlow -> {
                rootController.findModalController().popBackStack(key = null)
                rootController.findRootController().present(
                    screen = NavigationTree.Auth.AuthFlow.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
            }
            is DeleteProfileAction.OpenPreviousScreen -> {
                rootController.findModalController().popBackStack(key = null)
            }
            else -> {}
        }
    }
}