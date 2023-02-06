package orderdetails.loadingstate.presentation.viewmodel.model

import orderdetails.cargotype.domain.model.OrderLoadingCargoType

sealed interface OrderLoadingEvent {
    data class OnBoxesCountChanged(val count: String) : OrderLoadingEvent
    data class OnPalletsCountChanged(val count: String) : OrderLoadingEvent
    data class OnCargoTypeChanged(val type: OrderLoadingCargoType) : OrderLoadingEvent
    data class OnAdditionalOptionsChanged(val options: List<String>) : OrderLoadingEvent
    object OnBackClick : OrderLoadingEvent
    object OnDoneButtonClick : OrderLoadingEvent
    object OnPhotoClick : OrderLoadingEvent
    object ResetAction : OrderLoadingEvent
    object OnOpenCargoTypeBSClick : OrderLoadingEvent
    object OnOpenAdditionalOptBSClick : OrderLoadingEvent
}