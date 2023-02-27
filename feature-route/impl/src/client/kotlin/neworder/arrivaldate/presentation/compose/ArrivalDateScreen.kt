package neworder.arrivaldate.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import neworder.arrivaldate.presentation.viewmodel.ArrivalDateViewModel
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateAction
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateEvent
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import java.util.Date

@Composable
internal fun ArrivalDateScreen(onDateClick: (Date) -> Unit) {
    val rootController = LocalRootController.current
    ViewModel(factory = { ArrivalDateViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        ArrivalDateView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is ArrivalDateAction.UpdateNewOrderScreen -> {
                onDateClick((action.value as ArrivalDateAction.UpdateNewOrderScreen).date)
                viewModel.obtainEvent(ArrivalDateEvent.ResetAction)
            }
            ArrivalDateAction.OpenPreviousScreen -> {
                rootController.findModalController().popBackStack(null)
            }
        }
    }
}