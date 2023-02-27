package maxaddresses.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import maxaddresses.presentation.viewmodel.MaxAddressCountViewModel
import maxaddresses.presentation.viewmodel.model.MaxAddressCountAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun MaxAddressCountScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { MaxAddressCountViewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        MaxAddressCountView { viewModel.obtainEvent(it) }
        when (action.value) {
            MaxAddressCountAction.OpenPreviousScreen ->
                rootController.findModalController().popBackStack(null)
            else -> {}
        }
    }
}