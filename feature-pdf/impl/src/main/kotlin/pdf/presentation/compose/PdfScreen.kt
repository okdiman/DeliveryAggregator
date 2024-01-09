package pdf.presentation.compose

import PdfScreenParameters
import PdfType
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import pdf.presentation.viewmodel.PdfScreenViewModel
import pdf.presentation.viewmodel.model.PdfScreenAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.DeliveryAggregatorTheme

@Composable
fun PdfScreen(parameters: PdfScreenParameters) {
    val rootController = LocalRootController.current
    StoredViewModel(factory = { PdfScreenViewModel(parameters) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        PdfScreenView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            PdfScreenAction.OpenPreviousScreen -> {
                rootController.popBackStack()
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
private fun PdfScreen_Preview() {
    DeliveryAggregatorTheme {
        PdfScreen(PdfScreenParameters(PdfType.Offer))
    }
}
