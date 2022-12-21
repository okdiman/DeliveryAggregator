package user.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import presentation.parameters.UserParameters
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import user.presentation.viewmodel.UserViewModel
import user.presentation.viewmodel.model.UserAction
import user.presentation.viewmodel.model.UserEvent

@Composable
fun UserScreen(parameters: UserParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { UserViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        UserView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is UserAction.OpenPreviousStep -> {
                rootController.popBackStack()
                viewModel.obtainEvent(UserEvent.ResetAction)
            }
            is UserAction.OpenMainFlow -> {

            }
            else -> {}
        }
    }
}