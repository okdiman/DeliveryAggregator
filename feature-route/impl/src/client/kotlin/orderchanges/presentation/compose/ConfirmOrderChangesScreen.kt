package orderchanges.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import orderchanges.presentation.ConfirmOrderChangesParameters
import orderchanges.presentation.compose.view.ConfirmOrderChangesView
import orderchanges.presentation.viewmodel.ConfirmOrderChangesViewModel
import orderchanges.presentation.viewmodel.model.ConfirmOrderChangesAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun ConfirmOrderChangesScreen(parameters: ConfirmOrderChangesParameters) {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { ConfirmOrderChangesViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        ConfirmOrderChangesView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            ConfirmOrderChangesAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            else -> {}
        }
    }
}
