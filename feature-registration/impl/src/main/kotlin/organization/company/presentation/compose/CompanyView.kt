package organization.company.presentation.compose

import ActionButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import root.RegistrationConstants
import root.RegistrationConstants.Limits.Common.MAX_NAME_CHARS
import root.RegistrationConstants.Limits.Company.INN_CHARS
import root.RegistrationConstants.Limits.Company.KPP_CHARS
import root.RegistrationConstants.Limits.Company.OGRN_CHARS
import root.presentation.RegistrationTextField
import root.presentation.TitleRegistrationView
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
fun CompanyView(state: CompanyState, eventHandler: (CompanyEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(rememberScrollState())
    ) {
        TitleRegistrationView(
            step = RegistrationConstants.Step.ONE,
            imageRes = R.drawable.organization_info_ic,
            titleRes = R.string.organization_info
        )
        CompanyTextFieldsBlock(state, eventHandler)
    }
    ActionButton(enabled = state.isContinueButtonEnabled) {
        eventHandler(CompanyEvent.OnContinueButtonClick)
    }
}

@Composable
private fun CompanyTextFieldsBlock(
    state: CompanyState,
    eventHandler: (CompanyEvent) -> Unit
) {
    RegistrationTextField(
        title = stringResource(R.string.company_name),
        state = state.companyName,
        hint = stringResource(R.string.company_name_hint),
        maxChar = MAX_NAME_CHARS
    ) { eventHandler(CompanyEvent.OnCompanyNameChanged(it)) }
    RegistrationTextField(
        title = stringResource(R.string.inn),
        state = state.inn,
        isDigits = true,
        hint = stringResource(R.string.inn_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = INN_CHARS
    ) { eventHandler(CompanyEvent.OnInnChanged(it)) }
    RegistrationTextField(
        title = stringResource(R.string.kpp),
        state = state.kpp,
        isDigits = true,
        hint = stringResource(R.string.kpp_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = KPP_CHARS
    ) { eventHandler(CompanyEvent.OnKppChanged(it)) }
    RegistrationTextField(
        title = stringResource(R.string.ogrn),
        state = state.ogrn,
        isDigits = true,
        hint = stringResource(R.string.ogrn_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = OGRN_CHARS
    ) { eventHandler(CompanyEvent.OnOgrnChanged(it)) }
    RegistrationTextField(
        modifier = Modifier.clickable { eventHandler(CompanyEvent.OnLegalAddressClick) },
        title = stringResource(R.string.legal_address),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        },
        state = state.legalAddress,
        hint = stringResource(R.string.address_hint)
    )
    RegistrationTextField(
        modifier = Modifier.clickable { eventHandler(CompanyEvent.OnActualAddressClick) },
        title = stringResource(R.string.actual_address),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        },
        state = state.actualAddress,
        hint = stringResource(R.string.address_hint)
    )
    Spacer(modifier = Modifier.height(120.dp))
}