package additionalInfo.presentation.compose

import additionalInfo.presentation.AdditionalInfoParameters
import additionalInfo.presentation.viewmodel.AdditionalInfoViewModel
import additionalInfo.presentation.viewmodel.model.AdditionalInfoAction
import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun AdditionalInfoScreen(parameters: AdditionalInfoParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { AdditionalInfoViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        AdditionalInfoView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            AdditionalInfoAction.OpenPreviousScreen -> rootController.findModalController()
                .popBackStack(null)
            else -> {}
        }
    }
}