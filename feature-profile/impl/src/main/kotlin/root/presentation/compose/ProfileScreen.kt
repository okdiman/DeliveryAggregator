package root.presentation.compose

import PdfScreenParameters
import PdfType
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import editing.presentation.EditProfileParameters
import exit.presentation.compose.ExitScreen
import navigation.NavigationTree
import openNotificationSettings
import root.presentation.compose.view.ProfileView
import root.presentation.viewmodel.ProfileViewModel
import root.presentation.viewmodel.model.ProfileAction
import root.presentation.viewmodel.model.ProfileEvent
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import support.presentation.compose.SupportScreen
import transport.presentation.TransportProfileParameters
import utils.presentStandardBS

@Suppress("LongMethod")
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
                rootController.findModalController().presentStandardBS { SupportScreen() }
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }

            ProfileAction.OpenNotificationsSettings -> {
                openNotificationSettings(context)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }

            ProfileAction.OpenOffer -> {
                rootController.findRootController().push(
                    screen = NavigationTree.Profile.Pdf.name,
                    params = PdfScreenParameters(PdfType.Offer)
                )
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }

            ProfileAction.OpenPrivacyPolicy -> {
                rootController.findRootController().push(
                    screen = NavigationTree.Profile.Pdf.name,
                    params = PdfScreenParameters(PdfType.PrivacyPolicy)
                )
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }

            ProfileAction.OpenExitFromAccount -> {
                rootController.findModalController().presentStandardBS { ExitScreen() }
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }

            ProfileAction.OpenDevMenu -> {
                rootController.findRootController().push(
                    screen = NavigationTree.Main.DevMenu.name
                )
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }

            else -> {}
        }
    }
}