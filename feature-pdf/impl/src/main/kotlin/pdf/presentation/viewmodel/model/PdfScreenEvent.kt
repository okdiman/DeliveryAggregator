package pdf.presentation.viewmodel.model

sealed interface PdfScreenEvent {
    object OnBackClick : PdfScreenEvent
}