package organization.bank.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import organization.bank.presentation.viewmodel.BankViewModel
import organization.bank.presentation.viewmodel.model.BankAction
import organization.bank.presentation.viewmodel.model.BankEvent
import presentation.model.DefaultBankModel
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
            is BankAction.OpenNextStep -> {
                rootController.push(
                    screen = NavigationTree.Registration.Transport.name,
                    params = TransportParameters(
                        user = parameters.user,
                        company = parameters.company,
                        bank = DefaultBankModel(
                            paymentAcc = state.value.paymentAcc.text,
                            corrAcc = state.value.corrAcc.text,
                            bik = state.value.bik.text,
                            bankName = state.value.bankName.text
                        )
                    )
                )
                viewModel.obtainEvent(BankEvent.ResetAction)
            }
            is BankAction.OpenPreviousStep -> {
                rootController.popBackStack()
                viewModel.obtainEvent(BankEvent.ResetAction)
            }
            else -> {}
        }
    }
}