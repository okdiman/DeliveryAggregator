package comment.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import comment.presentation.AddressCommentParameters
import comment.presentation.viewmodel.AddressCommentViewModel
import comment.presentation.viewmodel.model.AddressCommentAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun AddressCommentScreen(parameters: AddressCommentParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { AddressCommentViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        AddressCommentView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            AddressCommentAction.OpenAddressesScreen -> {
                parameters.onSuggestClick(parameters.uiModel.copy(comment = state.value.comment.stateText))
                repeat(2) {
                    rootController.findModalController().popBackStack(null)
                }
            }
            AddressCommentAction.OpenPreviousScreen -> rootController.findModalController().popBackStack(null)
            else -> {}
        }
    }
}