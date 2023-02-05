package orderdetails.loadingstate.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import orderdetails.loadingstate.presentation.OrderStateParameters
import orderdetails.loadingstate.presentation.viewmodel.OrderLoadingViewModel
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun OrderLoadingScreen(parameters: OrderStateParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { OrderLoadingViewModel(parameters) }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        val state = viewModel.viewStates().observeAsState()
        OrderLoadingView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            OrderLoadingAction.OpenAdditionalOptionsScreen -> {}
            OrderLoadingAction.OpenCamera -> {}
            OrderLoadingAction.OpenCargoTypeScreen -> {}
            OrderLoadingAction.OpenPreviousScreen -> rootController.popBackStack()
            null -> {}
        }
    }
}