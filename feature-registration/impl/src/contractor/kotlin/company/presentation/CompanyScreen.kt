package company.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import organization.bank.presentation.BankParameters
import organization.company.presentation.compose.CompanyView
import organization.company.presentation.viewmodel.CompanyViewModel
import organization.company.presentation.viewmodel.model.CompanyAction
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import presentation.AddressSuggestUiModel
import presentation.parameters.CompanyParameters
import root.presentation.AddressBSScreen
import root.presentation.RegistrationCompanyModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import utils.UiConstants.BottomSheet.SCREEN_MAX_HEIGHT
import utils.presentStandardBS

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
                            companyName = state.value.companyName.stateText,
                            inn = state.value.inn.stateText,
                            kpp = state.value.kpp.stateText,
                            ogrn = state.value.ogrn.stateText,
                            legalAddress = state.value.legalAddress.stateText,
                            actualAddress = state.value.actualAddress.stateText
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
    onSuggestClick: (AddressSuggestUiModel) -> Unit,
    onFinishEvent: () -> Unit
) {
    val rootController = LocalRootController.current
    rootController.findModalController().presentStandardBS(maxHeight = SCREEN_MAX_HEIGHT) {
        AddressBSScreen(
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