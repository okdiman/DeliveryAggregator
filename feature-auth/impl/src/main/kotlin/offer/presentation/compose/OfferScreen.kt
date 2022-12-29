package offer.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import offer.presentation.viewmodel.OfferViewModel
import offer.presentation.viewmodel.model.OfferAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun OfferScreen() {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { OfferViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        OfferView(viewState = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            OfferAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }
            else -> {}
        }
    }
}