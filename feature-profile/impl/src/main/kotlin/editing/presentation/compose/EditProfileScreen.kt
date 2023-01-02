package editing.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import deleting.presentation.compose.DeleteProfileScreen
import editing.presentation.viewmodel.EditProfileViewModel
import editing.presentation.viewmodel.model.EditProfileAction
import editing.presentation.viewmodel.model.EditProfileEvent
import navigation.NavigationTree
import presentation.EditProfileParameters
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import ru.alexgladkov.odyssey.core.LaunchFlag
import utils.CommonConstants.BottomNavigation.PROFILE_TAB
import utils.UiConstants.BottomSheet.SCREEN_CORNER_RADIUS

@Composable
fun EditProfileScreen(parameters: EditProfileParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { EditProfileViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        EditProfileView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            EditProfileAction.OpenDeleteAccScreen -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS
                    )
                ) { DeleteProfileScreen() }
                viewModel.obtainEvent(EditProfileEvent.ResetAction)
            }
            EditProfileAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            EditProfileAction.OpenProfileScreenWithUpdate -> {
                rootController.present(
                    screen = NavigationTree.Main.MainFlow.name,
                    launchFlag = LaunchFlag.SingleInstance,
                    startTabPosition = PROFILE_TAB
                )
            }
            else -> {}
        }
    }
}