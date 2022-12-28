package root.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.NavigationTree
import presentation.EditProfileParameters
import root.presentation.viewmodel.ProfileViewModel
import root.presentation.viewmodel.model.ProfileAction
import root.presentation.viewmodel.model.ProfileEvent
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import utils.openNotificationSettings

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
            is ProfileAction.OpenTransport -> {
//                rootController.push(NavigationTree.Profile.Transport.name)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            is ProfileAction.OpenEditProfile -> {
                rootController.findRootController().push(
                    screen = NavigationTree.Profile.Edit.name,
                    params = EditProfileParameters(viewModel.getProfileModel())
                )
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            is ProfileAction.OpenDepartureAddress -> {
//                rootController.push(NavigationTree.Profile.DepartureAddress.name)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            is ProfileAction.OpenOffer -> {
                rootController.findRootController().push(NavigationTree.Profile.Offer.name)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            is ProfileAction.OpenSupport -> {
//                rootController.push(NavigationTree.Profile.Support.name)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            is ProfileAction.OpenNotificationsSettings -> {
                openNotificationSettings(context)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            is ProfileAction.OpenExitFromAccount -> {
//                rootController.push(NavigationTree.Profile.Exit.name)
                viewModel.obtainEvent(ProfileEvent.ResetAction)
            }
            else -> {}
        }
    }
}