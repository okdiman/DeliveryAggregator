package organization.bank.presentation.viewmodel.model

sealed interface BankEvent {
    data class OnPaymentAccChanged(val paymentAcc: String) : BankEvent
    data class OnCorrAccChanged(val corrAcc: String) : BankEvent
    data class OnBikChanged(val bik: String) : BankEvent
    data class OnBankNameChanged(val bankName: String) : BankEvent
    object OnContinueButtonClick : BankEvent
    object OnBackButtonClick : BankEvent
    object ResetAction : BankEvent
}