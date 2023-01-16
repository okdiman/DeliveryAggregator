package organization.bank.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import organization.bank.presentation.viewmodel.BankViewModel
import organization.bank.presentation.viewmodel.model.BankAction
import organization.bank.presentation.viewmodel.model.BankEvent
import presentation.parameters.model.RegistrationBankModel
import presentation.parameters.BankParameters
import presentation.parameters.TransportParameters
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun BankScreen(parameters: BankParameters) {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { BankViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        BankView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            BankAction.OpenNextStep -> {
                rootController.push(
                    screen = NavigationTree.Registration.Transport.name,
                    params = TransportParameters(
                        user = parameters.user,
                        company = parameters.company,
                        bank = RegistrationBankModel(
                            paymentAcc = state.value.paymentAcc.stateText,
                            corrAcc = state.value.corrAcc.stateText,
                            bik = state.value.bik.stateText,
                            bankName = state.value.bankName.stateText
                        )
                    )
                )
                viewModel.obtainEvent(BankEvent.ResetAction)
            }
            BankAction.OpenPreviousStep -> {
                rootController.popBackStack()
                viewModel.obtainEvent(BankEvent.ResetAction)
            }
            else -> {}
        }
    }
}