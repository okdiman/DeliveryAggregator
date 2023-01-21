package orderdetails.presentation.compose

import additionalInfo.presentation.compose.AdditionalInfoScreen
import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import orderdetails.presentation.OrderDetailsParameters
import orderdetails.presentation.viewmodel.OrderDetailsViewModel
import orderdetails.presentation.viewmodel.model.OrderDetailsAction
import orderdetails.presentation.viewmodel.model.OrderDetailsEvent
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import utils.UiConstants

@Composable
fun OrderDetailsScreen(parameters: OrderDetailsParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { OrderDetailsViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        OrderDetailsView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is OrderDetailsAction.OpenDeliveryStateScreen -> {
                viewModel.obtainEvent(OrderDetailsEvent.ResetAction)
            }
            is OrderDetailsAction.OpenLoadingStateScreen -> {
                viewModel.obtainEvent(OrderDetailsEvent.ResetAction)
            }
            OrderDetailsAction.OpenAdditionalInfo -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS,
                    )
                ) {
                    AdditionalInfoScreen(parameters = viewModel.getAdditionalInfoParams())
                }
                viewModel.obtainEvent(OrderDetailsEvent.ResetAction)
            }
            OrderDetailsAction.OpenPreviousScreen -> rootController.popBackStack()
            else -> {}
        }
    }
}