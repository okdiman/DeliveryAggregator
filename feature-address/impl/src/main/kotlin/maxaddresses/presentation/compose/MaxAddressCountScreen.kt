package maxaddresses.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import maxaddresses.presentation.viewmodel.MaxAddressCountViewModel
import maxaddresses.presentation.viewmodel.model.MaxAddressCountAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.DeliveryAggregatorTheme

@Composable
fun MaxAddressCountScreen() {
    val rootController = LocalRootController.current
    ViewModel(factory = { MaxAddressCountViewModel() }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        MaxAddressCountView { viewModel.obtainEvent(it) }
        when (action.value) {
            MaxAddressCountAction.OpenPreviousScreen -> rootController.findModalController()
                .popBackStack(null)

            else -> {}
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MaxAddressCountScreen_Preview() {
    DeliveryAggregatorTheme {
        MaxAddressCountScreen()
    }
}
