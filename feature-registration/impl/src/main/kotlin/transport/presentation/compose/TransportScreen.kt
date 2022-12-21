package transport.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import presentation.parameters.TransportParameters
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import transport.presentation.viewmodel.TransportViewModel
import transport.presentation.viewmodel.model.TransportAction
import transport.presentation.viewmodel.model.TransportEvent

@Composable
fun TransportScreen(parameters: TransportParameters) {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { TransportViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        TransportView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is TransportAction.OpenNextStep -> {
                rootController.push(
                    screen = NavigationTree.Registration.User.name
                )
                viewModel.obtainEvent(TransportEvent.ResetAction)
            }
            is TransportAction.OpenPreviousStep -> {
                rootController.popBackStack()
                viewModel.obtainEvent(TransportEvent.ResetAction)
            }
            else -> {}
        }
    }
}