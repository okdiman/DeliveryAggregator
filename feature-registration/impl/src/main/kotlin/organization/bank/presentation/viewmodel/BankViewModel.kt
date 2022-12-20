package organization.bank.presentation.viewmodel

import BaseViewModel
import organization.bank.presentation.viewmodel.model.BankAction
import organization.bank.presentation.viewmodel.model.BankEvent
import organization.bank.presentation.viewmodel.model.BankState
import root.RegistrationConstants.Limits.BANK_ACC_CHARS
import root.RegistrationConstants.Limits.BIK_CHARS
import root.RegistrationConstants.Limits.MIN_NAME_CHARS

class BankViewModel : BaseViewModel<BankState, BankAction, BankEvent>(
    initialState = BankState()
) {

    override fun obtainEvent(viewEvent: BankEvent) {
        when (viewEvent) {
            is BankEvent.OnPaymentAccChanged -> onPaymentAccChanged(viewEvent.paymentAcc)
            is BankEvent.OnCorrAccChanged -> onCorrAccChanged(viewEvent.corrAcc)
            is BankEvent.OnBikChanged -> onBikChanged(viewEvent.bik)
            is BankEvent.OnBankNameChanged -> onBankNameChanged(viewEvent.bankName)
            is BankEvent.OnContinueButtonClick -> onContinueButtonClick()
            is BankEvent.OnBackButtonClick -> onBackButtonClick()
            is BankEvent.ResetAction -> onResetAction()
        }
    }

    private fun onContinueButtonClick() {
        viewAction = BankAction.OpenNextStep
    }

    private fun onBackButtonClick() {
        viewAction = BankAction.OpenPreviousStep
    }

    private fun onPaymentAccChanged(newPayment: String) {
        viewState = viewState.copy(
            paymentAcc = viewState.paymentAcc.copy(
                text = newPayment
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(paymentAcc = newPayment)
        )
    }

    private fun onCorrAccChanged(newCorr: String) {
        viewState = viewState.copy(
            corrAcc = viewState.corrAcc.copy(
                text = newCorr
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(corrAcc = newCorr)
        )
    }

    private fun onBikChanged(newBik: String) {
        viewState = viewState.copy(
            bik = viewState.bik.copy(
                text = newBik
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(bik = newBik)
        )
    }

    private fun onBankNameChanged(newName: String) {
        viewState = viewState.copy(
            bankName = viewState.bankName.copy(
                text = newName
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(name = newName)
        )
    }

    private fun isContinueButtonEnabled(
        paymentAcc: String = viewState.paymentAcc.text,
        corrAcc: String = viewState.corrAcc.text,
        bik: String = viewState.bik.text,
        name: String = viewState.bankName.text,
    ): Boolean {
        return isPaymentAccFilled(paymentAcc) && isCorrAccFilled(corrAcc) && isBikFilled(bik) &&
                isNameFilled(name)
    }

    private fun isPaymentAccFilled(paymentAcc: String) = paymentAcc.length == BANK_ACC_CHARS
    private fun isCorrAccFilled(corrAcc: String) = corrAcc.length == BANK_ACC_CHARS
    private fun isBikFilled(bik: String) = bik.length == BIK_CHARS
    private fun isNameFilled(name: String) = name.length >= MIN_NAME_CHARS
}