package neworder.address.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import maxaddresses.presentation.compose.MaxAddressCountScreen
import neworder.address.presentation.viewmodel.NewOrderAddressViewModel
import neworder.address.presentation.viewmodel.NewOrderAddressViewModel.Companion.NEW_ID
import neworder.address.presentation.viewmodel.model.NewOrderAddressAction
import neworder.address.presentation.viewmodel.model.NewOrderAddressEvent
import presentation.model.AddressUiModel
import root.presentation.AddressBSScreen
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import trinity_monsters.delivery_aggregator.core_ui.R
import utils.UiConstants

@Suppress("LongMethod")
@Composable
fun NewOrderAddressScreen(
    onAddressClick: (AddressUiModel) -> Unit
) {
    val rootController = LocalRootController.current
    ViewModel(factory = { NewOrderAddressViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        NewOrderAddressView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            NewOrderAddressAction.OpenAddAddress -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS,
                        maxHeight = UiConstants.BottomSheet.SCREEN_MAX_HEIGHT
                    )
                ) {
                    AddressBSScreen(
                        state = state.value.bsAddress,
                        suggests = state.value.suggests,
                        isNeedComment = true,
                        onTextFieldChanged = {
                            viewModel.obtainEvent(NewOrderAddressEvent.OnBSAddressChanged(it))
                        },
                        onSuggestClick = {
                            viewModel.obtainEvent(NewOrderAddressEvent.OnSuggestAddressClick(NEW_ID, it))
                        },
                        onClearClick = {
                            viewModel.obtainEvent(NewOrderAddressEvent.OnBSAddressChanged(""))
                        }
                    )
                }
                viewModel.obtainEvent(NewOrderAddressEvent.ResetAction)
            }
            NewOrderAddressAction.OpenAddingError -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS
                    )
                ) { MaxAddressCountScreen() }
                viewModel.obtainEvent(NewOrderAddressEvent.ResetAction)
            }
            is NewOrderAddressAction.OpenAddressEdit -> {
                val id = (action.value as NewOrderAddressAction.OpenAddressEdit).id
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS,
                        maxHeight = UiConstants.BottomSheet.SCREEN_MAX_HEIGHT
                    )
                ) {
                    AddressBSScreen(
                        state = state.value.bsAddress,
                        suggests = state.value.suggests,
                        isNeedComment = true,
                        onTextFieldChanged = {
                            viewModel.obtainEvent(NewOrderAddressEvent.OnBSAddressChanged(it))
                        },
                        onSuggestClick = {
                            viewModel.obtainEvent(NewOrderAddressEvent.OnSuggestAddressClick(id, it))
                        },
                        onClearClick = {
                            viewModel.obtainEvent(NewOrderAddressEvent.OnBSAddressChanged(""))
                        },
                        titleRes = R.string.common_edit_address
                    )
                }
                viewModel.obtainEvent(NewOrderAddressEvent.ResetAction)
            }
            is NewOrderAddressAction.UpdateNewOrderScreen -> {
                onAddressClick((action.value as NewOrderAddressAction.UpdateNewOrderScreen).model)
                viewModel.obtainEvent(NewOrderAddressEvent.ResetAction)
            }
            NewOrderAddressAction.OpenPreviousScreen -> rootController.popBackStack()
            else -> {}
        }
    }
}