package orderdetails.loadingstate.presentation.viewmodel.model

import android.net.Uri
import orderdetails.loadingstate.presentation.compose.model.OrderLoadingParamState

data class OrderLoadingState(
    val photo: Uri? = null,
    val boxesCount: OrderLoadingParamState.BoxesCountState = OrderLoadingParamState.BoxesCountState(),
    val palletsCount: OrderLoadingParamState.PalletsCountState = OrderLoadingParamState.PalletsCountState(),
    val cargoType: OrderLoadingParamState.CargoTypeState = OrderLoadingParamState.CargoTypeState(),
    val additionalOptions: OrderLoadingParamState.AdditionalOptionsState = OrderLoadingParamState.AdditionalOptionsState(),
    val isDoneButtonVisible: Boolean = false
)