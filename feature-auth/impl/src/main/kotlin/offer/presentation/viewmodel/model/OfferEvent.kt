package offer.presentation.viewmodel.model

sealed class OfferEvent {
    object OnBackClick : OfferEvent()
}