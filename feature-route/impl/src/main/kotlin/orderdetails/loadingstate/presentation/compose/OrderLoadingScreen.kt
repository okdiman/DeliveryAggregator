package orderdetails.loadingstate.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import orderdetails.cargotype.presentation.CargoTypeScreen
import orderdetails.loadingstate.presentation.OrderStateParameters
import orderdetails.loadingstate.presentation.viewmodel.OrderLoadingViewModel
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingAction
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingEvent
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import utils.UiConstants.BottomSheet.SCREEN_CORNER_RADIUS

@Composable fun OrderLoadingScreen(parameters: OrderStateParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { OrderLoadingViewModel(parameters) }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        val state = viewModel.viewStates().observeAsState()
        OrderLoadingView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            OrderLoadingAction.OpenAdditionalOptionsScreen -> {
                viewModel.obtainEvent(OrderLoadingEvent.ResetAction)
            }
            OrderLoadingAction.OpenCamera -> {
                viewModel.obtainEvent(OrderLoadingEvent.ResetAction)
            }
            OrderLoadingAction.OpenCargoTypeScreen -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS
                    )
                ) {
                    CargoTypeScreen(state.value) { type ->
                        viewModel.obtainEvent(OrderLoadingEvent.OnCargoTypeChanged(type))
                    }
                }
                viewModel.obtainEvent(OrderLoadingEvent.ResetAction)
            }
            OrderLoadingAction.OpenPreviousScreen -> rootController.popBackStack()
            null -> {}
        }
    }
}