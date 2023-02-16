package departure.root.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import departure.maxaddresses.presentation.compose.DepartureMaxAddressCountScreen
import departure.root.presentation.compose.view.DepartureView
import departure.root.presentation.viewmodel.DepartureViewModel
import departure.root.presentation.viewmodel.DepartureViewModel.Companion.NEW_ID
import departure.root.presentation.viewmodel.model.DepartureAction
import departure.root.presentation.viewmodel.model.DepartureEvent
import presentation.AddressBSScreen
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import trinity_monsters.delivery_aggregator.feature_profile.impl.R
import utils.UiConstants.BottomSheet.SCREEN_CORNER_RADIUS
import utils.UiConstants.BottomSheet.SCREEN_MAX_HEIGHT

@Suppress("LongMethod")
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
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS,
                        maxHeight = SCREEN_MAX_HEIGHT
                    )
                ) {
                    AddressBSScreen(
                        state = state.value.bsAddress,
                        suggests = state.value.suggests,
                        onTextFieldChanged = {
                            viewModel.obtainEvent(DepartureEvent.OnBSAddressChanged(it))
                        },
                        onSuggestClick = {
                            viewModel.obtainEvent(DepartureEvent.OnSuggestAddressClick(NEW_ID, it))
                        },
                        onClearClick = {
                            viewModel.obtainEvent(DepartureEvent.OnBSAddressChanged(""))
                        }
                    )
                }
                viewModel.obtainEvent(DepartureEvent.ResetAction)
            }
            DepartureAction.OpenAddingError -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS
                    )
                ) { DepartureMaxAddressCountScreen() }
                viewModel.obtainEvent(DepartureEvent.ResetAction)
            }
            is DepartureAction.OpenAddressEdit -> {
                val id = (action.value as DepartureAction.OpenAddressEdit).id
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS,
                        maxHeight = SCREEN_MAX_HEIGHT
                    )
                ) {
                    AddressBSScreen(
                        state = state.value.bsAddress,
                        suggests = state.value.suggests,
                        onTextFieldChanged = {
                            viewModel.obtainEvent(DepartureEvent.OnBSAddressChanged(it))
                        },
                        onSuggestClick = {
                            viewModel.obtainEvent(DepartureEvent.OnSuggestAddressClick(id, it))
                        },
                        onClearClick = {
                            viewModel.obtainEvent(DepartureEvent.OnBSAddressChanged(""))
                        },
                        titleRes = R.string.departure_edit_address
                    )
                }
                viewModel.obtainEvent(DepartureEvent.ResetAction)
            }
            DepartureAction.OpenPreviousScreen -> rootController.popBackStack()
            else -> {}
        }
    }
}