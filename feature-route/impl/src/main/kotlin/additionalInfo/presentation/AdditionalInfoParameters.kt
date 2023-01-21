package additionalInfo.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class AdditionalInfoParameters(
    val marketplace: String,
    val organization: String,
    val boxesCount: String,
    val weight: String,
    val additionalOptions: String,
    val comment: String
)