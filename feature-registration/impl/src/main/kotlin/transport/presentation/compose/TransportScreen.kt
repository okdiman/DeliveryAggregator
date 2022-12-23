package transport.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import data.AddressConstants
import navigation.NavigationTree
import presentation.model.DepartureAddressModel
import presentation.model.RegistrationTransportModel
import presentation.parameters.TransportParameters
import presentation.parameters.UserParameters
import root.presentation.RegistrationAddressBSScreen
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import transport.presentation.viewmodel.TransportViewModel
import transport.presentation.viewmodel.model.TransportAction
import transport.presentation.viewmodel.model.TransportEvent

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
            is TransportAction.OpenNextStep -> {
                rootController.push(
                    screen = NavigationTree.Registration.User.name,
                    params = UserParameters(
                        user = parameters.user,
                        company = parameters.company,
                        bank = parameters.bank,
                        transport = RegistrationTransportModel(
                            licencePlate = state.value.licencePlate.text,
                            departureAddress = DepartureAddressModel(
                                geoLon = state.value.departureAddress.address?.geoLon.orEmpty(),
                                geoLat = state.value.departureAddress.address?.geoLat.orEmpty(),
                                city = state.value.departureAddress.address?.city.orEmpty(),
                                street = state.value.departureAddress.address?.street.orEmpty(),
                                house = state.value.departureAddress.address?.house.orEmpty()
                            ),
                            carBrand = state.value.carBrand.text,
                            carCategory = state.value.carCategory.text,
                            carLoadCapacity = state.value.carLoadCapacity.text,
                            carCapacity = state.value.carCapacity.text
                        )
                    )
                )
                viewModel.obtainEvent(TransportEvent.ResetAction)
            }
            is TransportAction.OpenPreviousStep -> {
                rootController.popBackStack()
                viewModel.obtainEvent(TransportEvent.ResetAction)
            }
            is TransportAction.OpenDepartureAddressBs -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        maxHeight = AddressConstants.SCREEN_MAX_HEIGHT,
                        cornerRadius = AddressConstants.SCREEN_CORNER_RADIUS,
                        closeOnBackdropClick = false,
                        closeOnSwipe = false
                    )
                ) {
                    RegistrationAddressBSScreen(
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