package transport.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import presentation.AddressBSScreen
import root.presentation.model.RegistrationDepartureAddressModel
import root.presentation.model.RegistrationTransportModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import transport.TransportParameters
import transport.presentation.viewmodel.TransportViewModel
import transport.presentation.viewmodel.model.TransportAction
import transport.presentation.viewmodel.model.TransportEvent
import transport.presentation.viewmodel.model.TransportState
import user.presentation.UserParameters
import utils.UiConstants.BottomSheet.SCREEN_CORNER_RADIUS
import utils.UiConstants.BottomSheet.SCREEN_MAX_HEIGHT

@Composable
fun TransportScreen(parameters: TransportParameters) {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { TransportViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        TransportView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            TransportAction.OpenNextStep -> {
                rootController.push(
                    screen = NavigationTree.Registration.User.name,
                    params = getUserParameters(parameters, state.value)
                )
                viewModel.obtainEvent(TransportEvent.ResetAction)
            }
            TransportAction.OpenPreviousStep -> {
                rootController.popBackStack()
                viewModel.obtainEvent(TransportEvent.ResetAction)
            }
            TransportAction.OpenDepartureAddressBs -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        maxHeight = SCREEN_MAX_HEIGHT,
                        cornerRadius = SCREEN_CORNER_RADIUS,
                        closeOnBackdropClick = false,
                        closeOnSwipe = false
                    )
                ) {
                    AddressBSScreen(
                        state = state.value.bsAddress,
                        suggests = state.value.suggests,
                        onClearClick = {
                            viewModel.obtainEvent(TransportEvent.OnBSAddressChanged(""))
                        },
                        onTextFieldChanged = {
                            viewModel.obtainEvent(TransportEvent.OnBSAddressChanged(it))
                        }
                    ) {
                        viewModel.obtainEvent(TransportEvent.OnSuggestAddressClick(it))
                    }
                }
                viewModel.obtainEvent(TransportEvent.ResetAction)
            }
            else -> {}
        }
    }
}

private fun getUserParameters(parameters: TransportParameters, state: TransportState) =
    UserParameters(
        user = parameters.user,
        company = parameters.company,
        bank = parameters.bank,
        transport = RegistrationTransportModel(
            licencePlate = state.licencePlate.stateText,
            departureAddress = RegistrationDepartureAddressModel(
                geoLon = state.departureAddress.address?.geoLon.orEmpty(),
                geoLat = state.departureAddress.address?.geoLat.orEmpty(),
                city = state.departureAddress.address?.city.orEmpty(),
                street = state.departureAddress.address?.street.orEmpty(),
                house = state.departureAddress.address?.house.orEmpty()
            ),
            carBrand = state.carBrand.stateText,
            carCategory = state.carCategory.stateText,
            carLoadCapacity = state.carLoadCapacity.stateText,
            carCapacity = state.carCapacity.stateText
        )
    )