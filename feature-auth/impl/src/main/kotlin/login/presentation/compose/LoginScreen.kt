package login.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import login.presentation.viewmodel.LoginViewModel
import login.presentation.viewmodel.model.LoginAction
import login.presentation.viewmodel.model.LoginEvent
import navigation.NavigationTree
import presentation.VerifyParameters
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun LoginScreen() {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { LoginViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        LoginView(viewState = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is LoginAction.OpenVerifyScreen -> {
                rootController.push(
                    screen = NavigationTree.Auth.Verify.name,
                    params = VerifyParameters(state.value.phone)
                )
                viewModel.obtainEvent(LoginEvent.ResetAction)
            }
            is LoginAction.OpenOffer -> {
                rootController.push(NavigationTree.Auth.Offer.name)
                viewModel.obtainEvent(LoginEvent.ResetAction)
            }
            else -> {}
        }
    }
}