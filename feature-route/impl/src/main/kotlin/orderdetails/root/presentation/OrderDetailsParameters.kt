package orderdetails.root.presentation

import androidx.compose.runtime.Immutable

@Immutable
class OrderDetailsParameters(
    val id: Long,
    val index: Int,
    val isNeedToUpdateAfterBack: Boolean
)