package organization.bank.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import organization.bank.presentation.viewmodel.BankViewModel
import organization.bank.presentation.viewmodel.model.BankAction
import organization.bank.presentation.viewmodel.model.BankEvent
import presentation.parameters.BankParameters
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