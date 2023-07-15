package pdf.presentation.viewmodel.model

sealed interface PdfScreenAction {
    object OpenPreviousScreen: PdfScreenAction
}