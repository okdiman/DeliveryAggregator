package editing.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import editing.presentation.viewmodel.EditProfileViewModel
import editing.presentation.viewmodel.model.EditProfileAction
import editing.presentation.viewmodel.model.EditProfileEvent
import presentation.EditProfileParameters
import ru.alexgladkov.odyssey.compose.local.LocalRootController

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
            is EditProfileAction.OpenDeleteAccScreen -> {
                viewModel.obtainEvent(EditProfileEvent.ResetAction)
            }
            is EditProfileAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            is EditProfileAction.ShowProfileUpdatedSnackbar -> {
                viewModel.obtainEvent(EditProfileEvent.ResetAction)
            }
            else -> {}
        }
    }
}