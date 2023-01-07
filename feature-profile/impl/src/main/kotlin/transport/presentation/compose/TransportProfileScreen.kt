package transport.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import presentation.TransportProfileParameters
import presentation.AddressBSScreen
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import transport.presentation.viewmodel.TransportProfileViewModel
import transport.presentation.viewmodel.model.TransportProfileAction
import transport.presentation.viewmodel.model.TransportProfileEvent
import utils.UiConstants

@Composable
fun TransportProfileScreen(parameters: TransportProfileParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { TransportProfileViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        TransportProfileView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            TransportProfileAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            TransportProfileAction.OpenDepartureAddressBs -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        maxHeight = UiConstants.BottomSheet.SCREEN_MAX_HEIGHT,
                        cornerRadius = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS,
                        closeOnBackdropClick = false,
                        closeOnSwipe = false
                    )
                ) {
                    AddressBSScreen(
                        state = state.value.bsAddress,
                        suggests = state.value.suggests,
                        onClearClick = {
                            viewModel.obtainEvent(TransportProfileEvent.OnBSAddressChanged(""))
                        },
                        onTextFieldChanged = {
                            viewModel.obtainEvent(TransportProfileEvent.OnBSAddressChanged(it))
                        }
                    ) {
                        viewModel.obtainEvent(TransportProfileEvent.OnSuggestAddressClick(it))
                    }
                }
                viewModel.obtainEvent(TransportProfileEvent.ResetAction)
            }
            else -> {}
        }
    }
}