package organization.company.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import organization.company.presentation.viewmodel.CompanyViewModel
import organization.company.presentation.viewmodel.model.CompanyAction
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import presentation.model.AddressUiModel
import presentation.model.RegistrationCompanyModel
import presentation.parameters.BankParameters
import presentation.parameters.CompanyParameters
import root.presentation.RegistrationAddressBSScreen
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import utils.UiConstants.BottomSheet.SCREEN_CORNER_RADIUS
import utils.UiConstants.BottomSheet.SCREEN_MAX_HEIGHT

@Composable
fun CompanyScreen(parameters: CompanyParameters) {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { CompanyViewModel(parameters) }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        val state = viewModel.viewStates().observeAsState()
        CompanyView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            CompanyAction.OpenNextStep -> {
                rootController.push(
                    screen = NavigationTree.Registration.Bank.name,
                    params = BankParameters(
                        user = parameters.user,
                        company = RegistrationCompanyModel(
                            companyName = state.value.companyName.text,
                            inn = state.value.inn.text,
                            kpp = state.value.kpp.text,
                            ogrn = state.value.ogrn.text,
                            legalAddress = state.value.legalAddress.address?.value,
                            actualAddress = state.value.actualAddress.address?.value
                        )
                    )
                )
                viewModel.obtainEvent(CompanyEvent.ResetAction)
            }
            CompanyAction.OpenSelectLegalAddress -> {
                ObtainBSScreenAction(
                    state = state,
                    onChangeEvent = { viewModel.obtainEvent(CompanyEvent.OnBSAddressChanged(it)) },
                    onFinishEvent = { viewModel.obtainEvent(CompanyEvent.ResetAction) },
                    onSuggestClick = {
                        viewModel.obtainEvent(CompanyEvent.OnLegalSuggestAddressClick(it))
                    }
                )
            }
            CompanyAction.OpenSelectActualAddress -> {
                ObtainBSScreenAction(
                    state = state,
                    onChangeEvent = { viewModel.obtainEvent(CompanyEvent.OnBSAddressChanged(it)) },
                    onFinishEvent = { viewModel.obtainEvent(CompanyEvent.ResetAction) },
                    onSuggestClick = {
                        viewModel.obtainEvent(CompanyEvent.OnActualSuggestAddressClick(it))
                    }
                )
            }
            else -> {}
        }
    }
}

@Composable
fun ObtainBSScreenAction(
    state: State<CompanyState>,
    onChangeEvent: (String) -> Unit,
    onSuggestClick: (AddressUiModel) -> Unit,
    onFinishEvent: () -> Unit
) {
    val rootController = LocalRootController.current
    rootController.findModalController().present(
        modalSheetConfiguration = ModalSheetConfiguration(
            maxHeight = SCREEN_MAX_HEIGHT,
            cornerRadius = SCREEN_CORNER_RADIUS,
            closeOnBackdropClick = false,
            closeOnSwipe = false
        )
    ) {
        RegistrationAddressBSScreen(
            state = state.value.bsAddress,
            suggests = state.value.suggests,
            onClearClick = { onChangeEvent("") },
            onTextFieldChanged = { onChangeEvent(it) }
        ) {
            onSuggestClick(it)
        }
    }
    onFinishEvent()
}