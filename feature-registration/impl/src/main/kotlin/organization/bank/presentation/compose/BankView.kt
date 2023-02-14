package organization.bank.presentation.compose

import ScrollScreenActionButton
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import organization.bank.presentation.viewmodel.model.BankEvent
import organization.bank.presentation.viewmodel.model.BankState
import root.RegistrationConstants
import root.RegistrationConstants.Limits.Bank.BANK_ACC_CHARS
import root.RegistrationConstants.Limits.Bank.BIK_CHARS
import root.presentation.RegistrationTitleView
import trinity_monsters.delivery_aggregator.feature_registration.impl.R
import utils.CommonConstants.LIMITS.Common.MAX_NAME_CHARS
import view.StandardTextField

@Composable
internal fun BankView(state: BankState, eventHandler: (BankEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(rememberScrollState())
    ) {
        RegistrationTitleView(
            isBackButtonVisible = true,
            onButtonClick = { eventHandler(BankEvent.OnBackButtonClick) },
            step = RegistrationConstants.Step.TWO,
            imageRes = R.drawable.organization_info_ic,
            titleRes = R.string.registration_organization_info
        )
        BankTextFieldsBlock(state, eventHandler)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            enabled = state.isContinueButtonEnabled
        ) {
            eventHandler(BankEvent.OnContinueButtonClick)
        }
    }
}

@Composable
fun BankTextFieldsBlock(state: BankState, eventHandler: (BankEvent) -> Unit) {
    StandardTextField(
        title = stringResource(R.string.bank_payment_acc),
        state = state.paymentAcc,
        hint = stringResource(R.string.bank_payment_acc_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = BANK_ACC_CHARS
    ) {
        eventHandler(BankEvent.OnPaymentAccChanged(it))
    }
    StandardTextField(
        title = stringResource(R.string.bank_corr_acc),
        state = state.corrAcc,
        isDigits = true,
        hint = stringResource(R.string.bank_corr_acc_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = BANK_ACC_CHARS
    ) {
        eventHandler(BankEvent.OnCorrAccChanged(it))
    }
    StandardTextField(
        title = stringResource(R.string.bank_bik),
        state = state.bik,
        hint = stringResource(R.string.bank_bik_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = BIK_CHARS
    ) {
        eventHandler(BankEvent.OnBikChanged(it))
    }
    StandardTextField(
        title = stringResource(R.string.bank_name),
        state = state.bankName,
        hint = stringResource(R.string.bank_name_hint),
        maxChar = MAX_NAME_CHARS
    ) {
        eventHandler(BankEvent.OnBankNameChanged(it))
    }
    Spacer(modifier = Modifier.height(120.dp))
}