package organization.company.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import organization.company.presentation.viewmodel.CompanyViewModel
import organization.company.presentation.viewmodel.model.CompanyAction
import organization.company.presentation.viewmodel.model.CompanyEvent
import presentation.model.RegistrationCompanyModel
import presentation.parameters.BankParameters
import presentation.parameters.CompanyParameters
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun CompanyScreen(parameters: CompanyParameters) {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { CompanyViewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        val state = viewModel.viewStates().observeAsState()
        CompanyView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is CompanyAction.OpenNextStep -> {
                rootController.push(
                    screen = NavigationTree.Registration.Bank.name,
                    params = BankParameters(
                        user = parameters.user,
                        company = RegistrationCompanyModel(
                            companyName = state.value.companyName.text,
                            inn = state.value.inn.text,
                            kpp = state.value.kpp.text,
                            ogrn = state.value.ogrn.text,
                            legalAddress = state.value.legalAddress.text,
                            actualAddress = state.value.actualAddress.text
                        )
                    )
                )
                viewModel.obtainEvent(CompanyEvent.ResetAction)
            }
            else -> {}
        }
    }
}