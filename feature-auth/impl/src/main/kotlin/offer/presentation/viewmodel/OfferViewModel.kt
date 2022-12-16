package offer.presentation.viewmodel

import BaseViewModel
import offer.domain.GetOfferUseCase
import offer.presentation.viewmodel.model.OfferAction
import offer.presentation.viewmodel.model.OfferEvent
import offer.presentation.viewmodel.model.OfferState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OfferViewModel : BaseViewModel<OfferState, OfferAction, OfferEvent>(
    initialState = OfferState()
), KoinComponent {

    private val getOfferData by inject<GetOfferUseCase>()

    init {
        getOffer()
    }

    override fun obtainEvent(viewEvent: OfferEvent) {
        when (viewEvent) {
            is OfferEvent.OnBackClick -> onBackCLick()
        }
    }

    private fun getOffer() {
        viewState = viewState.copy(
            offer = getOfferData(),
        )
    }

    private fun onBackCLick() {
        viewAction = OfferAction.OpenPreviousScreen
    }
}