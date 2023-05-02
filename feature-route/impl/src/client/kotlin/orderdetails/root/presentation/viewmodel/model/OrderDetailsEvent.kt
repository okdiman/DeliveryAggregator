package orderdetails.root.presentation.viewmodel.model

import android.net.Uri

sealed interface OrderDetailsEvent {
    object OnBackClick : OrderDetailsEvent
    object ResetAction : OrderDetailsEvent
    object OnRetryClick : OrderDetailsEvent
    object OnAdditionalInfoClick : OrderDetailsEvent
    object OnPayClick : OrderDetailsEvent
    object OnDeleteClick : OrderDetailsEvent
    data class OnOrderPhotoClick(val uri: Uri) : OrderDetailsEvent
}
