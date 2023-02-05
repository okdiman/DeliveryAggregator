package orderdetails.root.presentation.compose

import additionalInfo.presentation.compose.AdditionalInfoScreen
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import navigation.NavigationTree
import orderdetails.loadingstate.presentation.OrderStateParameters
import orderdetails.root.presentation.OrderDetailsParameters
import orderdetails.root.presentation.compose.view.OrderDetailsView
import orderdetails.root.presentation.viewmodel.OrderDetailsViewModel
import orderdetails.root.presentation.viewmodel.model.OrderDetailsAction
import orderdetails.root.presentation.viewmodel.model.OrderDetailsEvent
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.extensions.observeAsState
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import ru.alexgladkov.odyssey.core.LaunchFlag
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
                rootController.push(
                    screen = NavigationTree.Routes.DeliveryState.name,
                    params = OrderStateParameters((action.value as OrderDetailsAction.OpenDeliveryStateScreen).id)
                )
                viewModel.obtainEvent(OrderDetailsEvent.ResetAction)
            }
            is OrderDetailsAction.OpenLoadingStateScreen -> {
                rootController.push(
                    screen = NavigationTree.Routes.LoadingState.name,
                    params = OrderStateParameters((action.value as OrderDetailsAction.OpenLoadingStateScreen).id)
                )
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
            OrderDetailsAction.OpenPreviousScreen -> onBackClick(parameters, rootController)
            else -> {}
        }
    }
    /**
     * проверяем есть ли в стеке БШ доп инфы или нет
     */
    val stack = rootController.findModalController().currentStack.observeAsState()
    BackHandler {
        if (stack.value.isNullOrEmpty()) {
            onBackClick(parameters, rootController)
        } else {
            rootController.findModalController().popBackStack(null)
        }
    }
}

/**
 * нужно обновлять экран рейсов, если мы попали на деталку по клику на кнопку "принять заказ"
 */
private fun onBackClick(parameters: OrderDetailsParameters, rootController: RootController) {
    if (parameters.isNeedToUpdateAfterBack) {
        rootController.findRootController().present(
            screen = NavigationTree.Main.MainFlow.name,
            launchFlag = LaunchFlag.SingleInstance
        )
    } else {
        rootController.popBackStack()
    }
}