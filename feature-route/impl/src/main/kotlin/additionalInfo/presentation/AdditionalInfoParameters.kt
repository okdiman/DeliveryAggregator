package additionalInfo.presentation

import androidx.compose.runtime.Immutable
import orderdetails.domain.model.extras.OrderDetailsExtrasModel

@Immutable
data class AdditionalInfoParameters(
    val marketplace: String,
    val organization: String,
    val boxesCount: String,
    val weight: String,
    val additionalOptions: List<OrderDetailsExtrasModel>?,
    val comment: String
)