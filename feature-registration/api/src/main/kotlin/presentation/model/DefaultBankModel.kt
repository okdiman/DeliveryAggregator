package presentation.model

import androidx.compose.runtime.Immutable

@Immutable
class DefaultBankModel(
    val paymentAcc: String,
    val corrAcc: String,
    val bik: String,
    val bankName: String
)