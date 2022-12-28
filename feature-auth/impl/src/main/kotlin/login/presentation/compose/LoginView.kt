package login.presentation.compose

import ActionButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import login.presentation.viewmodel.model.LoginEvent
import login.presentation.viewmodel.model.LoginState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_auth.impl.R
import utils.CommonConstants.LIMITS.Common.MAX_PHONE_CHARS
import view.CommonTextField

@Composable
fun LoginView(viewState: LoginState, eventHandler: (LoginEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TitleBlock()
        PhoneBlock(viewState = viewState, eventHandler = eventHandler)
        AgreementBlock(viewState = viewState, eventHandler = eventHandler)
        ActionButton(
            modifier = Modifier.fillMaxSize(),
            textRes = R.string.entrance,
            enabled = viewState.isButtonEnabled,
            padding = PaddingValues(0.dp)
        ) { eventHandler(LoginEvent.OnEntranceButtonCLick) }
    }
}

@Composable
private fun TitleBlock() {
    Spacer(modifier = Modifier.height(38.dp))
    Image(
        modifier = Modifier.size(48.dp),
        painter = painterResource(R.drawable.login_phone_ic),
        contentDescription = "poster"
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = stringResource(R.string.enter_phone),
        style = Theme.fonts.regular
    )
}

@Composable
private fun PhoneBlock(viewState: LoginState, eventHandler: (LoginEvent) -> Unit) {
    Spacer(modifier = Modifier.height(4.dp))
    Row {
        Text(
            text = stringResource(id = R.string.phone_prefix),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        CommonTextField(
            text = viewState.phone,
            textStyle = Theme.fonts.bold.copy(fontSize = 24.sp),
            hint = stringResource(id = R.string.phone_hint),
            onValueChanged = {
                eventHandler(LoginEvent.PhoneChanged(it))
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            isPhone = true,
            maxChar = MAX_PHONE_CHARS
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AgreementBlock(viewState: LoginState, eventHandler: (LoginEvent) -> Unit) {
    Spacer(modifier = Modifier.height(4.dp))
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        /**
        Без провайдера используются отступы, которые нельзя контролировать
         */
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            Checkbox(
                checked = viewState.isAgreementChecked,
                colors = CheckboxDefaults.colors(
                    checkedColor = Theme.colors.textPrimaryColor
                ),
                onCheckedChange = {
                    eventHandler(LoginEvent.OnAgreementClick)
                }
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            Text(
                text = stringResource(id = R.string.agreement),
                style = Theme.fonts.regular
            )
            Text(
                modifier = Modifier.clickable {
                    eventHandler(LoginEvent.OnOfferCLick)
                },
                text = stringResource(id = R.string.read_offer),
                style = Theme.fonts.regular.copy(
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}