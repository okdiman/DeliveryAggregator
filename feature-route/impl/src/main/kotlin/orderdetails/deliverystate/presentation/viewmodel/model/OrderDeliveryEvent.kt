package orderdetails.deliverystate.presentation.viewmodel.model

import android.net.Uri
import permissions.AppPermissionState

sealed interface OrderDeliveryEvent {
    data class OnPermissionStateChanged(val state: AppPermissionState) : OrderDeliveryEvent
    data class OnProblemStateChanged(val isProblem: Boolean) : OrderDeliveryEvent
    data class OnCommentChanged(val comment: String) : OrderDeliveryEvent
    data class OnPhotoAdded(val uri: Uri) : OrderDeliveryEvent
    object OnPhotoClick : OrderDeliveryEvent
    object OnDoneButtonClick : OrderDeliveryEvent
    object OnBackClick : OrderDeliveryEvent
    object OnRationaleDismiss : OrderDeliveryEvent
    object ResetAction : OrderDeliveryEvent
}