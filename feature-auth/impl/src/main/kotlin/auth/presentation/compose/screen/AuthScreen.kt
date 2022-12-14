package auth.presentation.compose.screen

import androidx.compose.runtime.Composable
import auth.presentation.compose.view.AuthView
import auth.presentation.viewmodel.AuthViewModel
import auth.presentation.viewmodel.model.AuthAction
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import utils.NavigationTree

@Composable
fun AuthScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { AuthViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        AuthView(viewState = state.value) { event ->
            viewModel.obtainEvent(event)
        }

        when (action.value) {
            is AuthAction.OpenMainFlow -> {

            }
            is AuthAction.OpenRegistrationFlow -> {

            }
            is AuthAction.OpenOffer -> {
                rootController.push(NavigationTree.Auth.Offer.name)
            }
            else -> {}
        }
    }
}