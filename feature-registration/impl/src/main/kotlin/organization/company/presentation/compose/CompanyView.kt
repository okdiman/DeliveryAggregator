package organization.company.presentation.compose

import ScrollScreenActionButton
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import modifiers.autoScrollInFocus
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import root.RegistrationConstants
import root.RegistrationConstants.Limits.Company.KPP_CHARS
import root.RegistrationConstants.Limits.Company.OGRN_CHARS
import root.presentation.RegistrationTitleView
import trinity_monsters.delivery_aggregator.feature_registration.impl.R
import utils.CommonConstants.LIMITS.Common.MAX_NAME_CHARS
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun CompanyView(state: CompanyState, eventHandler: (CompanyEvent) -> Unit) {
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
            step = RegistrationConstants.Step.ONE,
            imageRes = R.drawable.organization_info_ic,
            titleRes = R.string.registration_organization_info
        )
        CompanyTextFieldsBlock(
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
            eventHandler(CompanyEvent.OnContinueButtonClick)
        }
    }
}

@Suppress("LongMethod")
@Composable
private fun CompanyTextFieldsBlock(
    modifier: Modifier,
    state: CompanyState,
    eventHandler: (CompanyEvent) -> Unit
) {
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.company_inn),
        state = state.inn,
        isDigits = true,
        hint = stringResource(R.string.company_inn_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = RegistrationConstants.Limits.Company.INN_CHARS
    ) { eventHandler(CompanyEvent.OnInnChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.common_company_name),
        state = state.companyName,
        hint = stringResource(R.string.company_name_hint),
        maxChar = MAX_NAME_CHARS
    ) { eventHandler(CompanyEvent.OnCompanyNameChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.company_kpp),
        state = state.kpp,
        isDigits = true,
        hint = stringResource(R.string.company_kpp_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = KPP_CHARS
    ) { eventHandler(CompanyEvent.OnKppChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.company_ogrn),
        state = state.ogrn,
        isDigits = true,
        hint = stringResource(R.string.company_ogrn_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = OGRN_CHARS
    ) { eventHandler(CompanyEvent.OnOgrnChanged(it)) }
    StandardTextField(
        modifier = Modifier.clickable { eventHandler(CompanyEvent.OnLegalAddressClick) },
        title = stringResource(R.string.company_legal_address),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        },
        state = state.legalAddress,
        hint = stringResource(R.string.company_address_hint)
    )
    StandardTextField(
        modifier = Modifier.clickable { eventHandler(CompanyEvent.OnActualAddressClick) },
        title = stringResource(R.string.company_actual_address),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        },
        state = state.actualAddress,
        hint = stringResource(R.string.company_address_hint)
    )
    Spacer(modifier = Modifier.height(120.dp))
}