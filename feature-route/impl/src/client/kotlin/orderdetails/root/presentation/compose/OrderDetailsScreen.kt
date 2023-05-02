package orderdetails.root.presentation.compose

import additionalInfo.presentation.compose.AdditionalInfoScreen
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import deleteorder.presentation.DeleteOrderParameters
import deleteorder.presentation.compose.DeleteOrderScreen
import openBrowser
import orderdetails.root.presentation.OrderDetailsParameters
import orderdetails.root.presentation.compose.view.OrderDetailsView
import orderdetails.root.presentation.viewmodel.OrderDetailsViewModel
import orderdetails.root.presentation.viewmodel.model.OrderDetailsAction
import orderdetails.root.presentation.viewmodel.model.OrderDetailsEvent
import ru.alexgladkov.odyssey.compose.extensions.observeAsState
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.AlertConfiguration
import utils.presentStandardBS
import view.ImageViewerDialog

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
                rootController.findModalController().presentStandardBS {
                    AdditionalInfoScreen(parameters = viewModel.getAdditionalInfoParams())
                }
                viewModel.obtainEvent(OrderDetailsEvent.ResetAction)
            }
            OrderDetailsAction.OpenPreviousScreen -> rootController.popBackStack()
            OrderDetailsAction.OpenDeleteOrderScreen -> {
                rootController.findModalController()
                    .presentStandardBS { DeleteOrderScreen(DeleteOrderParameters(parameters.id)) }
                viewModel.obtainEvent(OrderDetailsEvent.ResetAction)
            }
            is OrderDetailsAction.OpenPaymentInBrowser -> {
                val uri = (action.value as OrderDetailsAction.OpenPaymentInBrowser).paymentUri
                openBrowser(LocalContext.current, uri)
                viewModel.obtainEvent(OrderDetailsEvent.ResetAction)
            }
            is OrderDetailsAction.OpenImageViewerDialog -> {
                val uri = (action.value as OrderDetailsAction.OpenImageViewerDialog).uri
                rootController.findModalController().present(
                    alertConfiguration = AlertConfiguration()
                ) {
                    ImageViewerDialog(
                        onDismissRequest = {
                            rootController.findModalController().popBackStack(null)
                        },
                        imageUri = uri
                    )
                }
                viewModel.obtainEvent(OrderDetailsEvent.ResetAction)
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