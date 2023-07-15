package pdf.presentation.viewmodel

import BaseViewModel
import PdfScreenParameters
import PdfType
import org.koin.core.component.KoinComponent
import pdf.presentation.viewmodel.model.PdfScreenAction
import pdf.presentation.viewmodel.model.PdfScreenEvent
import pdf.presentation.viewmodel.model.PdfScreenState
import trinity_monsters.delivery_aggregator.feature_pdf.impl.R

class PdfScreenViewModel(params: PdfScreenParameters) :
    BaseViewModel<PdfScreenState, PdfScreenAction, PdfScreenEvent>(initialState = PdfScreenState()), KoinComponent {

    init {
        viewState = PdfScreenState(
            rawId = when (params.pdfType) {
                PdfType.Offer -> R.raw.offer
                PdfType.PrivacyPolicy -> R.raw.privacy_policy
            }
        )
    }

    override fun obtainEvent(viewEvent: PdfScreenEvent) {
        when (viewEvent) {
            PdfScreenEvent.OnBackClick -> onBackCLick()
        }
    }

    private fun onBackCLick() {
        viewAction = PdfScreenAction.OpenPreviousScreen
    }
}