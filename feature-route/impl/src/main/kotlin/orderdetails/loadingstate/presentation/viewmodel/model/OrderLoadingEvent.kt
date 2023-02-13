package orderdetails.loadingstate.presentation.viewmodel.model

import android.net.Uri
import orderdetails.cargotype.domain.model.OrderLoadingCargoType
import orderdetails.loadingstate.presentation.compose.model.OrderLoadingExtrasUiModel
import permissions.AppPermissionState

sealed interface OrderLoadingEvent {
    data class OnPermissionStateChanged(val state: AppPermissionState) : OrderLoadingEvent
    data class OnBoxesCountChanged(val count: String) : OrderLoadingEvent
    data class OnPalletsCountChanged(val count: String) : OrderLoadingEvent
    data class OnCargoTypeChanged(val type: OrderLoadingCargoType) : OrderLoadingEvent
    data class OnExtrasChanged(val extras: List<OrderLoadingExtrasUiModel>) : OrderLoadingEvent
    data class OnPhotoAdded(val uri: Uri) : OrderLoadingEvent
    object OnBackClick : OrderLoadingEvent
    object OnDoneButtonClick : OrderLoadingEvent
    object OnPhotoClick : OrderLoadingEvent
    object ResetAction : OrderLoadingEvent
    object OnOpenCargoTypeBSClick : OrderLoadingEvent
    object OnOpenExtrasBSClick : OrderLoadingEvent
    object OnRationaleDismiss : OrderLoadingEvent
}