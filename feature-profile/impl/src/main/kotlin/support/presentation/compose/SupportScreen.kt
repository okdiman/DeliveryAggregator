package support.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import openDial
import openEmailSender
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import support.presentation.viewmodel.SupportViewModel
import support.presentation.viewmodel.model.SupportAction
import support.presentation.viewmodel.model.SupportEvent

@Composable
fun SupportScreen() {
    val rootController = LocalRootController.current
    val context = LocalContext.current
    ViewModel(factory = { SupportViewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        SupportView { viewModel.obtainEvent(it) }
        when (action.value) {
            SupportAction.OpenDialIntent -> {
                openDial(context)
                viewModel.obtainEvent(SupportEvent.ResetAction)
            }
            SupportAction.OpenSendEmailIntent -> {
                openEmailSender(context)
                viewModel.obtainEvent(SupportEvent.ResetAction)
            }
            SupportAction.OpenPreviousScreen -> {
                rootController.findModalController().popBackStack(key = null)
            }
            else -> {}
        }
    }
}