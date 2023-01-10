package departure.root.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import departure.maxaddresses.presentation.compose.DepartureMaxAddressCountScreen
import departure.root.presentation.viewmodel.DepartureViewModel
import departure.root.presentation.viewmodel.model.DepartureAction
import departure.root.presentation.viewmodel.model.DepartureEvent
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import utils.UiConstants

@Composable
fun DepartureScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { DepartureViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        DepartureView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            DepartureAction.OpenAddAddress -> {

                viewModel.obtainEvent(DepartureEvent.ResetAction)
            }
            DepartureAction.OpenAddingError -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS
                    )
                ) { DepartureMaxAddressCountScreen() }
                viewModel.obtainEvent(DepartureEvent.ResetAction)
            }
            is DepartureAction.OpenAddressEdit -> {

                viewModel.obtainEvent(DepartureEvent.ResetAction)
            }
            DepartureAction.OpenPreviousScreen -> rootController.findRootController().popBackStack()
            else -> {}
        }
    }
}