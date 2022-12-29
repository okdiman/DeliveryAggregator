package offer.presentation.viewmodel.model

sealed interface OfferEvent {
    object OnBackClick : OfferEvent
}