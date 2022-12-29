package organization.bank.presentation.viewmodel

import BaseViewModel
import organization.bank.presentation.viewmodel.model.BankAction
import organization.bank.presentation.viewmodel.model.BankEvent
import organization.bank.presentation.viewmodel.model.BankState
import root.RegistrationConstants.Limits.Bank.BANK_ACC_CHARS
import root.RegistrationConstants.Limits.Bank.BIK_CHARS
import utils.CommonConstants.LIMITS.Common.MIN_NAME_CHARS
import utils.isTextFieldFilled

class BankViewModel : BaseViewModel<BankState, BankAction, BankEvent>(
    initialState = BankState()
) {

    override fun obtainEvent(viewEvent: BankEvent) {
        when (viewEvent) {
            is BankEvent.OnPaymentAccChanged -> onPaymentAccChanged(viewEvent.paymentAcc)
            is BankEvent.OnCorrAccChanged -> onCorrAccChanged(viewEvent.corrAcc)
            is BankEvent.OnBikChanged -> onBikChanged(viewEvent.bik)
            is BankEvent.OnBankNameChanged -> onBankNameChanged(viewEvent.bankName)
            BankEvent.OnContinueButtonClick -> onContinueButtonClick()
            BankEvent.OnBackButtonClick -> onBackButtonClick()
            BankEvent.ResetAction -> onResetAction()
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
                text = newPayment,
                isPaymentAccError = !isTextFieldFilled(newPayment, BANK_ACC_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(paymentAcc = newPayment)
        )
    }

    private fun onCorrAccChanged(newCorr: String) {
        viewState = viewState.copy(
            corrAcc = viewState.corrAcc.copy(
                text = newCorr,
                isCorrAccError = !isTextFieldFilled(newCorr, BANK_ACC_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(corrAcc = newCorr)
        )
    }

    private fun onBikChanged(newBik: String) {
        viewState = viewState.copy(
            bik = viewState.bik.copy(
                text = newBik,
                isBikError = !isTextFieldFilled(newBik, BIK_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(bik = newBik)
        )
    }

    private fun onBankNameChanged(newName: String) {
        viewState = viewState.copy(
            bankName = viewState.bankName.copy(
                text = newName,
                isBankNameError = !isTextFieldFilled(newName, MIN_NAME_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(name = newName)
        )
    }

    private fun isContinueButtonEnabled(
        paymentAcc: String = viewState.paymentAcc.text,
        corrAcc: String = viewState.corrAcc.text,
        bik: String = viewState.bik.text,
        name: String = viewState.bankName.text,
    ) = isTextFieldFilled(paymentAcc, BANK_ACC_CHARS) &&
            isTextFieldFilled(corrAcc, BANK_ACC_CHARS) && isTextFieldFilled(bik, BIK_CHARS) &&
            isTextFieldFilled(name, MIN_NAME_CHARS)
}