package root.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import exit.presentation.compose.ExitScreen
import navigation.NavigationTree
import openNotificationSettings
import presentation.EditProfileParameters
import presentation.TransportProfileParameters
import root.presentation.viewmodel.ProfileViewModel
import root.presentation.viewmodel.model.ProfileAction
import root.presentation.viewmodel.model.ProfileEvent
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import support.presentation.compose.SupportScreen
import utils.UiConstants.BottomSheet.SCREEN_CORNER_RADIUS

@Composable
fun ProfileScreen() {
    val rootController = LocalRootController.current
    val context = LocalContext.current
    StoredViewModel(factory = { ProfileViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        ProfileView(state = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            ProfileAction.OpenEditProfile -> {
                runCatching {
                    rootController.findRootController().push(
                        screen = NavigationTree.Profile.Edit.name,
                        params = EditProfileParameters(viewModel.getProfileModel())
                    )
                }
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            ProfileAction.OpenDepartureAddress -> {
                rootController.findRootController()
                    .push(NavigationTree.Profile.DepartureAddress.name)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            ProfileAction.OpenTransport -> {
                rootController.findRootController().push(
                    screen = NavigationTree.Profile.Transport.name,
                    params = TransportProfileParameters(viewModel.getProfileModel())
                )
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            ProfileAction.OpenSupport -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS
                    )
                ) { SupportScreen() }
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            ProfileAction.OpenNotificationsSettings -> {
                openNotificationSettings(context)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            ProfileAction.OpenOffer -> {
                rootController.findRootController().push(NavigationTree.Profile.Offer.name)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            ProfileAction.OpenExitFromAccount -> {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = SCREEN_CORNER_RADIUS
                    )
                ) { ExitScreen() }
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            else -> {}
        }
    }
}