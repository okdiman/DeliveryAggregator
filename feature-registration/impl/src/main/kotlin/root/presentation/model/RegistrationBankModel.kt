package root.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
class RegistrationBankModel(
    val paymentAcc: String,
    val corrAcc: String,
    val bik: String,
    val bankName: String
)