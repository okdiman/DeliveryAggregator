package departure.maxaddresses.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import departure.maxaddresses.presentation.viewmodel.DepartureMaxAddressCountViewModel
import departure.maxaddresses.presentation.viewmodel.model.DepartureMaxAddressCountAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun DepartureMaxAddressCountScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { DepartureMaxAddressCountViewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        DepartureMaxAddressCountView { viewModel.obtainEvent(it) }
        when (action.value) {
            DepartureMaxAddressCountAction.OpenPreviousScreen ->
                rootController.findModalController().popBackStack(null)
            else -> {}
        }
    }
}