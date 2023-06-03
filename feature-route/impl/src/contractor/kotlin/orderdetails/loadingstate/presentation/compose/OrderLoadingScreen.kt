package orderdetails.loadingstate.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cargotype.presentation.CargoTypeScreen
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import extras.presentation.ExtrasScreen
import orderdetails.loadingstate.presentation.viewmodel.OrderLoadingViewModel
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingAction
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingEvent
import orderdetails.root.presentation.OrderStatesParameters
import permissions.PermissionsConstants
import permissions.presentation.PermissionHandler
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.UiConstants.BottomSheet.SCREEN_CORNER_RADIUS

@Composable
fun OrderLoadingScreen(parameters: OrderStatesParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { OrderLoadingViewModel(parameters) }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        val state = viewModel.viewStates().observeAsState()
        OrderLoadingView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            OrderLoadingAction.OpenExtrasScreen -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS
                    )
                ) {
                    ExtrasScreen(
                        state = state.value.extras,
                        onExtraCountChanged = { extra ->
                            viewModel.obtainEvent(OrderLoadingEvent.OnExtrasCountChanged(extra))
                        },
                        onExtrasClick = { extras ->
                            viewModel.obtainEvent(OrderLoadingEvent.OnExtrasChanged(extras))
                        }
                    )
                }
                viewModel.obtainEvent(OrderLoadingEvent.ResetAction)
            }

            OrderLoadingAction.OpenCamera -> {
                PermissionHandler(
                    permission = PermissionsConstants.Camera,
                    title = stringResource(id = R.string.loading_camera_permission),
                    state = state.value,
                    onPermissionStateChanged = {
                        viewModel.obtainEvent(OrderLoadingEvent.OnPermissionStateChanged(it))
                    },
                    onRationaleDismiss = {
                        viewModel.obtainEvent(OrderLoadingEvent.OnRationaleDismiss)
                    }
                )
                viewModel.obtainEvent(OrderLoadingEvent.ResetAction)
            }

            OrderLoadingAction.OpenCargoTypeScreen -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS
                    )
                ) {
                    CargoTypeScreen(state.value.cargoType) { type ->
                        viewModel.obtainEvent(OrderLoadingEvent.OnCargoTypeChanged(type))
                    }
                }
                viewModel.obtainEvent(OrderLoadingEvent.ResetAction)
            }

            OrderLoadingAction.OpenPreviousScreen -> rootController.popBackStack()
            else -> {}
        }
    }
}