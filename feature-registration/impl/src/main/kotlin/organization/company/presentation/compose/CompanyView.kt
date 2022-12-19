package organization.company.presentation.compose

import ActionButton
import androidx.compose.foundation.Image
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import root.RegistrationConstants.Limits.MAX_INN_CHARS
import root.RegistrationConstants.Limits.MAX_KPP_CHARS
import root.RegistrationConstants.Limits.MAX_NAME_CHARS
import root.RegistrationConstants.Limits.MAX_OGRN_CHARS
import root.RegistrationConstants.Step.ONE
import root.presentation.RegistrationTextField
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R

@Composable
fun CompanyView(state: CompanyState, eventHandler: (CompanyEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = String.format(
                stringResource(R.string.registration_step),
                ONE
            ),
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
        Spacer(Modifier.height(30.dp))
        Image(
            painter = painterResource(R.drawable.organization_info_ic),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.organization_info),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        CompanyTextFieldsBlock(state, eventHandler)
    }
    ButtonBlock(state, eventHandler)
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
    ) {
        eventHandler(CompanyEvent.OnNameChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.inn),
        state = state.inn,
        hint = stringResource(R.string.inn_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = MAX_INN_CHARS
    ) {
        eventHandler(CompanyEvent.OnInnChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.kpp),
        state = state.kpp,
        hint = stringResource(R.string.kpp_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = MAX_KPP_CHARS
    ) {
        eventHandler(CompanyEvent.OnKppChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.ogrn),
        state = state.ogrn,
        hint = stringResource(R.string.ogrn_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = MAX_OGRN_CHARS
    ) {
        eventHandler(CompanyEvent.OnOgrnChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.legal_address),
        state = state.legalAddress,
        hint = stringResource(R.string.address_hint)
    ) {
        eventHandler(CompanyEvent.OnLegalAddressChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.actual_address),
        state = state.actualAddress,
        hint = stringResource(R.string.address_hint)
    ) {
        eventHandler(CompanyEvent.OnActualAddressChanged(it))
    }
    Spacer(modifier = Modifier.height(120.dp))
}

@Composable
private fun ButtonBlock(state: CompanyState, eventHandler: (CompanyEvent) -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 44.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ActionButton(
            enabled = state.isContinueButtonEnabled,
            text = stringResource(R.string.continue_button)
        ) {
            eventHandler(CompanyEvent.OnContinueButtonClick)
        }
    }
}