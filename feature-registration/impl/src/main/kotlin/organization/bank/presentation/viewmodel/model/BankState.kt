package organization.bank.presentation.viewmodel.model

import organization.bank.presentation.compose.model.BankParamState

data class BankState(
    val paymentAcc: BankParamState.PaymentAccState = BankParamState.PaymentAccState(),
    val corrAcc: BankParamState.CorrAccState = BankParamState.CorrAccState(),
    val bik: BankParamState.BikState = BankParamState.BikState(),
    val bankName: BankParamState.BankNameState = BankParamState.BankNameState(),
    val isBankInfoLoaded: Boolean = false,
    val isContinueButtonEnabled: Boolean = false
)