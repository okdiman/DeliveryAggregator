package orderdetails.root.presentation.compose

import additionalInfo.presentation.compose.AdditionalInfoScreen
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import openBrowser
import orderdetails.root.presentation.OrderDetailsParameters
import orderdetails.root.presentation.compose.view.OrderDetailsView
import orderdetails.root.presentation.viewmodel.OrderDetailsViewModel
import orderdetails.root.presentation.viewmodel.model.OrderDetailsAction
import orderdetails.root.presentation.viewmodel.model.OrderDetailsEvent
import ru.alexgladkov.odyssey.compose.extensions.observeAsState
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
            is OrderDetailsAction.OpenPaymentInBrowser -> {
                val uri = (action.value as OrderDetailsAction.OpenPaymentInBrowser).paymentUri
                openBrowser(LocalContext.current, uri)
            }
            else -> {}
        }
    }

    val stack = rootController.findModalController().currentStack.observeAsState()
    BackHandler {
        if (stack.value.isNullOrEmpty()) {
            rootController.popBackStack()
        } else {
            rootController.findModalController().popBackStack(null)
        }
    }
}
