package orderdetails.root.domain.model

import android.net.Uri
import org.threeten.bp.LocalDateTime

data class OrderLoadModel(
    val loadDateTime: LocalDateTime,
    val images: List<Uri>
)
