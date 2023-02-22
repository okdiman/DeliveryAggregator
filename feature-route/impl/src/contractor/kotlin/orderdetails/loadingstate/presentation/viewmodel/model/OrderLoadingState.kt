package orderdetails.loadingstate.presentation.viewmodel.model

import orderdetails.loadingstate.presentation.compose.model.OrderLoadingParamState
import permissions.AppPermissionState
import view.model.PermissionState
import view.model.PhotoParamState

data class OrderLoadingState(
    val photo: PhotoParamState? = null,
    val boxesCount: OrderLoadingParamState.BoxesCountState = OrderLoadingParamState.BoxesCountState(),
    val palletsCount: OrderLoadingParamState.PalletsCountState = OrderLoadingParamState.PalletsCountState(),
    val cargoType: OrderLoadingParamState.CargoTypeState = OrderLoadingParamState.CargoTypeState(),
    val extras: OrderLoadingParamState.ExtrasState = OrderLoadingParamState.ExtrasState(),
    val isDoneButtonVisible: Boolean = false,
    val cameraPermissionState: AppPermissionState? = null
) : PermissionState(cameraPermissionState)