package orderdetails.root.presentation.compose.model

import android.net.Uri

data class OrderDeliveryUiModel(
    val deliveryDateTime: String,
    val imageUrl: Uri?
)
