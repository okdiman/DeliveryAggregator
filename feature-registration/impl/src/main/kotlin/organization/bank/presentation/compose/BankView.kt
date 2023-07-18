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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import modifiers.autoScrollInFocus
import organization.bank.presentation.viewmodel.model.BankEvent
import organization.bank.presentation.viewmodel.model.BankState
import root.RegistrationConstants
import root.RegistrationConstants.Limits.Bank.BANK_ACC_CHARS
import root.presentation.RegistrationTitleView
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_registration.impl.R
import view.StandardTextField

@Composable
internal fun BankView(state: BankState, eventHandler: (BankEvent) -> Unit) {
    val buttonHeight = remember {
        mutableStateOf(0f)
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(scrollState)
    ) {
        RegistrationTitleView(
            isBackButtonVisible = true,
            onButtonClick = { eventHandler(BankEvent.OnBackButtonClick) },
            step = RegistrationConstants.Step.TWO,
            imageRes = R.drawable.organization_info_ic,
            titleRes = R.string.registration_organization_info
        )
        BankTextFieldsBlock(
            modifier = Modifier.autoScrollInFocus(scrollState, buttonHeight),
            state = state,
            eventHandler = eventHandler
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            enabled = state.isContinueButtonEnabled,
            onPositioned = { buttonHeight.value = it }
        ) {
            eventHandler(BankEvent.OnContinueButtonClick)
        }
    }
}

@Composable
private fun BankTextFieldsBlock(
    modifier: Modifier,
    state: BankState,
    eventHandler: (BankEvent) -> Unit
) {
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.bank_bik),
        state = state.bik,
        hint = stringResource(R.string.bank_bik_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = RegistrationConstants.Limits.Bank.BIK_CHARS
    ) { eventHandler(BankEvent.OnBikChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.bank_payment_acc),
        state = state.paymentAcc,
        hint = stringResource(R.string.bank_payment_acc_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = BANK_ACC_CHARS
    ) { eventHandler(BankEvent.OnPaymentAccChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.bank_corr_acc),
        state = state.corrAcc,
        hint = stringResource(R.string.bank_corr_acc_hint),
        enabled = !state.isBankInfoLoaded,
        textStyle = if (state.isBankInfoLoaded) {
            Theme.fonts.regular.copy(color = Theme.colors.disabledTextColor)
        } else {
            Theme.fonts.regular.copy(platformStyle = null, lineHeightStyle = null)
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = BANK_ACC_CHARS
    ) {
        eventHandler(BankEvent.OnCorrAccChanged(it))
    }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.bank_name),
        state = state.bankName,
        hint = stringResource(R.string.bank_name_hint),
        enabled = !state.isBankInfoLoaded,
        textStyle = if (state.isBankInfoLoaded) {
            Theme.fonts.regular.copy(color = Theme.colors.disabledTextColor)
        } else {
            Theme.fonts.regular.copy(platformStyle = null, lineHeightStyle = null)
        }
    ) {
        eventHandler(BankEvent.OnBankNameChanged(it))
    }
    Spacer(modifier = Modifier.height(120.dp))
}