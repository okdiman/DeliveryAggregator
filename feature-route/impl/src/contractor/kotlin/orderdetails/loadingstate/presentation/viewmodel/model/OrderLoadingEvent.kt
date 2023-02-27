package orderdetails.loadingstate.presentation.viewmodel.model

import android.net.Uri
import cargotype.domain.model.CargoType
import extras.presentation.model.ExtrasUiModel
import permissions.AppPermissionState

sealed interface OrderLoadingEvent {
    data class OnPermissionStateChanged(val state: AppPermissionState) : OrderLoadingEvent
    data class OnBoxesCountChanged(val count: String) : OrderLoadingEvent
    data class OnPalletsCountChanged(val count: String) : OrderLoadingEvent
    data class OnCargoTypeChanged(val type: CargoType) : OrderLoadingEvent
    data class OnExtrasChanged(val extras: List<ExtrasUiModel>) : OrderLoadingEvent
    data class OnPhotoAdded(val uri: Uri) : OrderLoadingEvent
    object OnBackClick : OrderLoadingEvent
    object OnDoneButtonClick : OrderLoadingEvent
    object OnPhotoClick : OrderLoadingEvent
    object ResetAction : OrderLoadingEvent
    object OnOpenCargoTypeBSClick : OrderLoadingEvent
    object OnOpenExtrasBSClick : OrderLoadingEvent
    object OnRationaleDismiss : OrderLoadingEvent
}