package additionalInfo.presentation

import androidx.compose.runtime.Immutable
import orderdetails.root.domain.model.extras.OrderDetailsExtrasModel

@Immutable
data class AdditionalInfoParameters(
    val marketplace: String,
    val organization: String,
    val boxesCount: String,
    val weight: String,
    val extras: List<OrderDetailsExtrasModel>?,
    val comment: String
)