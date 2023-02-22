package additionalInfo.presentation

import androidx.compose.runtime.Immutable
import root.domain.model.extras.OrderExtrasModel

@Immutable
class AdditionalInfoParameters(
    val marketplace: String,
    val organization: String,
    val boxesCount: String,
    val weight: String,
    val extras: List<OrderExtrasModel>?,
    val comment: String
)
