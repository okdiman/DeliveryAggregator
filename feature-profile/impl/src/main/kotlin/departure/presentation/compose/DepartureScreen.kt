package departure.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import departure.presentation.viewmodel.DepartureViewModel
import departure.presentation.viewmodel.model.DepartureAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun DepartureScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { DepartureViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        DepartureView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            DepartureAction.OpenAddAddress -> {}
            DepartureAction.OpenAddingError -> {}
            DepartureAction.OpenAddressEdit -> {}
            DepartureAction.OpenPreviousScreen -> rootController.findRootController().popBackStack()
            else -> {}
        }
    }
}