package organization.bank.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import organization.bank.presentation.viewmodel.model.BankEvent
import organization.bank.presentation.viewmodel.model.BankState
import root.RegistrationConstants
import root.RegistrationConstants.Limits.Bank.BANK_ACC_CHARS
import root.RegistrationConstants.Limits.Bank.BIK_CHARS
import root.RegistrationConstants.Limits.Common.MAX_NAME_CHARS
import root.presentation.RegistrationTextField
import root.presentation.TitleRegistrationView
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R

@Composable
fun BankView(state: BankState, eventHandler: (BankEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(rememberScrollState())
    ) {
        TitleRegistrationView(
            isBackButtonVisible = true,
            onButtonClick = { eventHandler(BankEvent.OnBackButtonClick) },
            step = RegistrationConstants.Step.TWO,
            imageRes = R.drawable.organization_info_ic,
            titleRes = R.string.organization_info
        )
        BankTextFieldsBlock(state, eventHandler)
    }
    ActionButton(modifier = Modifier.fillMaxSize(), enabled = state.isContinueButtonEnabled) {
        eventHandler(BankEvent.OnContinueButtonClick)
    }
}

@Composable
fun BankTextFieldsBlock(state: BankState, eventHandler: (BankEvent) -> Unit) {
    RegistrationTextField(
        title = stringResource(R.string.payment_acc),
        state = state.paymentAcc,
        hint = stringResource(R.string.payment_acc_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = BANK_ACC_CHARS
    ) {
        eventHandler(BankEvent.OnPaymentAccChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.corr_acc),
        state = state.corrAcc,
        isDigits = true,
        hint = stringResource(R.string.corr_acc_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = BANK_ACC_CHARS
    ) {
        eventHandler(BankEvent.OnCorrAccChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.bik),
        state = state.bik,
        hint = stringResource(R.string.bik_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = BIK_CHARS
    ) {
        eventHandler(BankEvent.OnBikChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.bank_name),
        state = state.bankName,
        hint = stringResource(R.string.bank_name_hint),
        maxChar = MAX_NAME_CHARS
    ) {
        eventHandler(BankEvent.OnBankNameChanged(it))
    }
    Spacer(modifier = Modifier.height(120.dp))
}