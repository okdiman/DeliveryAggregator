package orderdetails.deliverystate.presentation.viewmodel.model

import orderdetails.deliverystate.presentation.compose.model.OrderDeliveryParamState
import permissions.AppPermissionState
import view.model.PermissionState
import view.model.PhotoParamState

data class OrderDeliveryState(
    val photo: PhotoParamState? = null,
    val comment: OrderDeliveryParamState.CommentState = OrderDeliveryParamState.CommentState(),
    val isProblem: Boolean = false,
    val isDoneButtonVisible: Boolean = false,
    val cameraPermissionState: AppPermissionState? = null
) : PermissionState(cameraPermissionState)