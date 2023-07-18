package login.presentation.compose

import ActionButton
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import login.presentation.viewmodel.model.LoginEvent
import login.presentation.viewmodel.model.LoginState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_auth.impl.R
import utils.CommonConstants.LIMITS.Common.MAX_PHONE_CHARS
import view.CheckboxView
import view.CommonTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun LoginView(state: LoginState, eventHandler: (LoginEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TitleBlock()
        PhoneBlock(viewState = state, eventHandler = eventHandler)
        AgreementBlock(viewState = state, eventHandler = eventHandler)
        ActionButton(
            modifier = Modifier.fillMaxSize(),
            textRes = R.string.login_entrance,
            enabled = state.isButtonEnabled,
            padding = PaddingValues(0.dp)
        ) { eventHandler(LoginEvent.OnEntranceButtonCLick) }
    }
    if (state.isError) {
        Toast.makeText(LocalContext.current, R_core.string.common_smth_wrong, Toast.LENGTH_SHORT).show()
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
    Spacer(modifier = Modifier.height(18.dp))
    Text(
        text = stringResource(R.string.login_enter_phone),
        style = Theme.fonts.regular
    )
}

@Composable
private fun PhoneBlock(viewState: LoginState, eventHandler: (LoginEvent) -> Unit) {
    Spacer(modifier = Modifier.height(12.dp))
    Row {
        Text(
            text = stringResource(id = R.string.login_phone_prefix),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        CommonTextField(
            text = viewState.phone,
            textStyle = Theme.fonts.bold.copy(fontSize = 24.sp),
            hint = stringResource(id = R.string.login_phone_hint),
            onValueChanged = {
                eventHandler(LoginEvent.PhoneChanged(it))
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            isPhone = true,
            maxChar = MAX_PHONE_CHARS
        )
    }
}

@Composable
private fun AgreementBlock(viewState: LoginState, eventHandler: (LoginEvent) -> Unit) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        CheckboxView(
            checked = viewState.isAgreementChecked,
            colors = CheckboxDefaults.colors(
                checkedColor = Theme.colors.textPrimaryColor
            ),
            onCheckedChange = {
                eventHandler(LoginEvent.OnAgreementClick)
            }
        )
        Spacer(modifier = Modifier.width(4.dp))
        AnnotatedClickableText(eventHandler)
    }
}

@Composable
private fun AnnotatedClickableText(eventHandler: (LoginEvent) -> Unit) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Theme.colors.textPrimaryColor)) {
            append(stringResource(id = R.string.login_agreement_part_1))
        }

        pushStringAnnotation(
            tag = Annotations.Offer.name,
            annotation = Annotations.Offer.name
        )
        withStyle(
            style = SpanStyle(
                color = Theme.colors.textPrimaryColor,
                textDecoration = TextDecoration.Underline
            )
        ) { append(stringResource(id = R.string.login_agreement_part_2)) }
        pop()

        withStyle(style = SpanStyle(color = Theme.colors.textPrimaryColor)) {
            append(stringResource(id = R.string.login_agreement_part_3))
        }

        pushStringAnnotation(
            tag = Annotations.Offer.name,
            annotation = Annotations.PrivacyPolicy.name
        )
        withStyle(
            style = SpanStyle(
                color = Theme.colors.textPrimaryColor,
                textDecoration = TextDecoration.Underline
            )
        ) { append(stringResource(id = R.string.login_agreement_part_4)) }
        pop()
    }

    ClickableText(
        text = annotatedText,
        style = Theme.fonts.regular.copy(fontSize = 14.sp),
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = Annotations.Offer.name,
                start = offset,
                end = offset
            ).forEach { annotation ->
                when (annotation.item) {
                    Annotations.Offer.name -> eventHandler(LoginEvent.OnOfferClick)
                    Annotations.PrivacyPolicy.name -> eventHandler(LoginEvent.OnPrivacyPolicyClick)
                }
            }
        }
    )
}

private enum class Annotations {
    Offer, PrivacyPolicy
}