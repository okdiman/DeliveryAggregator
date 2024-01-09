package pdf.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import pdf.presentation.viewmodel.model.PdfScreenEvent
import pdf.presentation.viewmodel.model.PdfScreenState
import theme.DeliveryAggregatorTheme
import view.BackButtonView

@Composable
internal fun PdfScreenView(state: PdfScreenState, eventHandler: (PdfScreenEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        BackButtonView(modifier = Modifier.padding(start = 16.dp)) { eventHandler(PdfScreenEvent.OnBackClick) }
        Spacer(modifier = Modifier.height(12.dp))
        if (state.rawId != null) {
            val pdfState =
                rememberVerticalPdfReaderState(resource = ResourceType.Asset(state.rawId))
            VerticalPDFReader(
                state = pdfState,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
private fun PdfScreen_Preview() {
    DeliveryAggregatorTheme {
        PdfScreenView(PdfScreenState()) {}
    }
}
