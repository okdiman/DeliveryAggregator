package organization.company.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import organization.company.presentation.viewmodel.CompanyViewModel
import organization.company.presentation.viewmodel.model.CompanyAction
import organization.company.presentation.viewmodel.model.CompanyEvent
import presentation.CompanyParameters
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
                    screen = NavigationTree.Registration.Bank.name
                )
                viewModel.obtainEvent(CompanyEvent.ResetAction)
            }
            else -> {}
        }
    }
}