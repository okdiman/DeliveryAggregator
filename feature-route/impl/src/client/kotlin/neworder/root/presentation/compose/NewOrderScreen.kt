package neworder.root.presentation.compose

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import cargotype.presentation.CargoTypeScreen
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import extras.presentation.ExtrasScreen
import navigation.NavigationTree
import neworder.address.presentation.compose.NewOrderAddressScreen
import neworder.arrivaldate.presentation.compose.ArrivalDateScreen
import neworder.arrivaltime.presentation.ArrivalTimeScreen
import neworder.cancellation.presentation.compose.CancellationOrderScreen
import neworder.ordercreated.presentation.OrderCreatedParameters
import neworder.root.presentation.compose.view.NewOrderView
import neworder.root.presentation.viewmodel.NewOrderViewModel
import neworder.root.presentation.viewmodel.model.NewOrderAction
import neworder.root.presentation.viewmodel.model.NewOrderEvent
import neworder.storage.presentation.compose.NewOrderStorageParameters
import ru.alexgladkov.odyssey.compose.extensions.observeAsState
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import utils.UiConstants.BottomSheet.SCREEN_CORNER_RADIUS

@Suppress("LongMethod")
@Composable
fun NewOrderScreen() {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { NewOrderViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        NewOrderView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            NewOrderAction.OpenAddressScreen -> {
                OpenModalScreen {
                    NewOrderAddressScreen {
                        viewModel.obtainEvent(NewOrderEvent.OnBSAddressChanged(it))
                    }
                }
                viewModel.obtainEvent(NewOrderEvent.ResetAction)
            }
            NewOrderAction.OpenCargoTypeScreen -> {
                OpenModalScreen {
                    CargoTypeScreen(state.value.cargoType) { type ->
                        viewModel.obtainEvent(NewOrderEvent.OnCargoTypeChanged(type))
                    }
                }
                viewModel.obtainEvent(NewOrderEvent.ResetAction)
            }
            NewOrderAction.OpenDateScreen -> {
                OpenModalScreen {
                    ArrivalDateScreen { date ->
                        viewModel.obtainEvent(NewOrderEvent.OnArrivalDateChanged(date))
                    }
                }
                viewModel.obtainEvent(NewOrderEvent.ResetAction)
            }
            NewOrderAction.OpenExtrasScreen -> {
                OpenModalScreen {
                    ExtrasScreen(state.value.extras) { extras ->
                        viewModel.obtainEvent(NewOrderEvent.OnExtrasChanged(extras))
                    }
                }
                viewModel.obtainEvent(NewOrderEvent.ResetAction)
            }
            NewOrderAction.OpenPreviousScreen -> {
                OpenModalScreen { CancellationOrderScreen() }
                viewModel.obtainEvent(NewOrderEvent.ResetAction)
            }
            NewOrderAction.OpenStorageScreen -> {
                rootController.push(
                    screen = NavigationTree.NewOrder.Storages.name,
                    params = NewOrderStorageParameters(
                        state.value.storage
                    ) { viewModel.obtainEvent(NewOrderEvent.OnStorageChanged(it)) }
                )
                viewModel.obtainEvent(NewOrderEvent.ResetAction)
            }
            is NewOrderAction.OpenSuccessScreen -> {
                rootController.push(
                    screen = NavigationTree.NewOrder.OrderCreated.name,
                    params = OrderCreatedParameters(
                        date = (action.value as NewOrderAction.OpenSuccessScreen).date
                    )
                )
            }
            is NewOrderAction.OpenCreationErrorScreen -> {
                rootController.findRootController().push(
                    NavigationTree.NewOrder.CreationError.name,
                    params = (action.value as NewOrderAction.OpenCreationErrorScreen).parameters
                )
            }
            NewOrderAction.OpenTimeScreen -> {
                OpenModalScreen {
                    ArrivalTimeScreen(state.value.arrivalTime) { arrivalTime ->
                        viewModel.obtainEvent(NewOrderEvent.OnArrivalTimeChanged(arrivalTime))
                    }
                }
                viewModel.obtainEvent(NewOrderEvent.ResetAction)
            }
            else -> {}
        }
        val stack = rootController.findModalController().currentStack.observeAsState()
        BackHandler {
            if (stack.value.isNullOrEmpty()) {
                viewModel.obtainEvent(NewOrderEvent.OnBackClick)
            } else {
                rootController.findModalController().popBackStack(null)
            }
        }
    }
}

@Composable
private fun OpenModalScreen(
    screen: @Composable () -> Unit
) {
    val rootController = LocalRootController.current
    rootController.findModalController().present(
        modalSheetConfiguration = ModalSheetConfiguration(
            cornerRadius = SCREEN_CORNER_RADIUS
        )
    ) { screen() }
}