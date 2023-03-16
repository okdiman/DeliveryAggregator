package orderdetails.root.domain.model

import android.net.Uri
import org.threeten.bp.LocalDateTime

data class OrderDeliveryModel(
    val deliveryDateTime: LocalDateTime,
    val images: List<Uri>
)
