package organization.bank.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import di.modules.NAMING_VALIDATOR_QUALIFIER
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import organization.bank.domain.GetBankInfoUseCase
import organization.bank.presentation.BankParameters
import organization.bank.presentation.viewmodel.model.BankAction
import organization.bank.presentation.viewmodel.model.BankEvent
import organization.bank.presentation.viewmodel.model.BankState
import root.RegistrationConstants.Limits.Bank.BANK_ACC_CHARS
import root.RegistrationConstants.Limits.Bank.BIK_CHARS
import utils.CommonConstants.LIMITS.Common.MIN_NAME_CHARS
import utils.ext.isTextFieldFilled
import utils.validators.domain.TextFieldValidator

class BankViewModel(private val parameters: BankParameters) : BaseViewModel<BankState, BankAction, BankEvent>(
    initialState = BankState()
), KoinComponent {
    private val namingValidator by inject<TextFieldValidator>(named(NAMING_VALIDATOR_QUALIFIER))
    private val getBankInfoUseCase by inject<GetBankInfoUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private var bikJob: Job? = null

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
                stateText = newPayment,
                isFillingError = !newPayment.isTextFieldFilled(BANK_ACC_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(paymentAcc = viewState.paymentAcc.copy(stateText = newPayment))
            )
        )
    }

    private fun onCorrAccChanged(newCorr: String) {
        viewState = viewState.copy(
            corrAcc = viewState.corrAcc.copy(
                stateText = newCorr,
                isFillingError = !newCorr.isTextFieldFilled(BANK_ACC_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(corrAcc = viewState.corrAcc.copy(stateText = newCorr))
            )
        )
    }

    private fun onBikChanged(newBik: String) {
        viewState = viewState.copy(
            bik = viewState.bik.copy(
                stateText = newBik,
                isFillingError = !newBik.isTextFieldFilled(BIK_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(bik = viewState.bik.copy(stateText = newBik))
            )
        )
        if (newBik.isTextFieldFilled(BIK_CHARS)) {
            bikJob?.cancel()
            bikJob = launchJob(appDispatchers.network) {
                delay(BIK_DELAY)
                getBankInfoUseCase(newBik, parameters.user.code.toInt(), parameters.user.phone)?.let { bank ->
                    viewState = viewState.copy(
                        bankName = viewState.bankName.copy(stateText = bank.name, isValidationError = false),
                        corrAcc = viewState.corrAcc.copy(stateText = bank.correspondentAccount, isFillingError = false),
                        isBankInfoLoaded = true
                    )
                }
            }
        }
    }

    private fun onBankNameChanged(newName: String) {
        val isValid = namingValidator.isValidate(newName)
        viewState = viewState.copy(
            bankName = viewState.bankName.copy(
                stateText = newName,
                isFillingError = !newName.isTextFieldFilled(MIN_NAME_CHARS),
                isValidationError = !isValid
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(
                    bankName = viewState.bankName.copy(
                        stateText = newName,
                        isValidationError = !isValid
                    )
                )
            )
        )
    }

    private fun isContinueButtonEnabled(state: BankState) =
        state.paymentAcc.stateText.isTextFieldFilled(BANK_ACC_CHARS) &&
            state.corrAcc.stateText.isTextFieldFilled(BANK_ACC_CHARS) &&
            state.bik.stateText.isTextFieldFilled(BIK_CHARS) &&
            state.bankName.stateText.isTextFieldFilled(MIN_NAME_CHARS) &&
            !state.bankName.isValidationError

    private companion object {
        const val BIK_DELAY = 500L
    }
}