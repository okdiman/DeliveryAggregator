package root.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import data.AddressConstants
import organization.company.presentation.viewmodel.model.CompanyState
import presentation.AddressUiModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration

@Composable
fun ObtainBSScreenAction(
    state: State<CompanyState>,
    onChangeEvent: (String) -> Unit,
    onSuggestClick: (AddressUiModel) -> Unit,
    onFinishEvent: () -> Unit
) {
    val rootController = LocalRootController.current
    rootController.findModalController().present(
        modalSheetConfiguration = ModalSheetConfiguration(
            maxHeight = AddressConstants.SCREEN_MAX_HEIGHT,
            cornerRadius = AddressConstants.SCREEN_CORNER_RADIUS,
            closeOnBackdropClick = false,
            closeOnSwipe = false
        )
    ) {
        RegistrationAddressBSScreen(
            state = state.value.bsAddress,
            suggests = state.value.addressList,
            onClearClick = { onChangeEvent("") },
            onTextFieldChanged = { onChangeEvent(it) }
        ) {
            onSuggestClick(it)
        }
    }
    onFinishEvent()
}