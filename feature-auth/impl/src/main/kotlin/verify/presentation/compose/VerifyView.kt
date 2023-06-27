package verify.presentation.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import root.AuthConstants.Limits.MAX_CODE_CHARS
import root.AuthConstants.Limits.SECOND
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_auth.impl.R
import utils.formatters.formatTicks
import verify.presentation.compose.model.VerifyStepError
import verify.presentation.viewmodel.model.VerifyEvent
import verify.presentation.viewmodel.model.VerifyState
import view.BackButtonView
import view.CommonTextField
import view.ProgressIndicator
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun VerifyView(viewState: VerifyState, eventHandler: (VerifyEvent) -> Unit) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 12.dp)) {
        BackButtonView { eventHandler(VerifyEvent.OnBackClick) }
        Spacer(modifier = Modifier.height(22.dp))
        InfoBlock(viewState = viewState)
        CodeBlock(viewState = viewState, eventHandler = eventHandler)
        if (viewState.isLoading) {
            ProgressIndicator(
                modifier = Modifier
                    .padding(start = 16.dp, top = 19.dp)
                    .width(54.dp)
            )
        }
    }
}

@Composable
private fun InfoBlock(viewState: VerifyState) {
    Image(
        modifier = Modifier.size(48.dp),
        painter = painterResource(R.drawable.verify_phone_ic),
        contentDescription = "poster"
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = viewState.title,
        style = Theme.fonts.regular
    )
}

@Suppress("LongMethod")
@Composable
private fun CodeBlock(viewState: VerifyState, eventHandler: (VerifyEvent) -> Unit) {
    val context = LocalContext.current
    var ticks by remember { mutableStateOf(viewState.renewalPeriod) }
    LaunchedEffect(viewState.isTimerVisible) {
        launch {
            ticks = viewState.renewalPeriod
            while (ticks > 0) {
                delay(SECOND)
                ticks--
            }
            eventHandler(VerifyEvent.TickerFinished)
        }
    }
    val focusRequester = remember { FocusRequester() }
    Spacer(modifier = Modifier.height(12.dp))
    CommonTextField(
        modifier = Modifier.focusRequester(focusRequester),
        text = viewState.code,
        textStyle = Theme.fonts.bold.copy(fontSize = 24.sp),
        hint = stringResource(id = R.string.verify_code_hint),
        onValueChanged = {
            eventHandler(VerifyEvent.OnCodeChanged(it))
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        isCode = true,
        maxChar = MAX_CODE_CHARS,
        enabled = !viewState.isLoading
    )
    if (viewState.error == VerifyStepError.Forbidden) {
        Text(
            text = stringResource(id = R.string.verify_code_error),
            style = Theme.fonts.regular.copy(fontSize = 14.sp, color = Theme.colors.errorColor)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
    if (viewState.error == VerifyStepError.UserAlreadyExist) {
        Text(
            text = stringResource(id = R.string.verify_user_already_exist),
            style = Theme.fonts.regular.copy(fontSize = 14.sp, color = Theme.colors.errorColor)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
    if (viewState.isTimerVisible) {
        Text(
            text = buildString {
                append(stringResource(id = R.string.verify_send_code_again_timer))
                append(formatTicks(ticks))
                append(stringResource(id = R.string.verify_seconds))
            },
            style = Theme.fonts.regular.copy(fontSize = 14.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
    if (viewState.isRetryButtonAvailable) {
        Text(
            modifier = Modifier.clickable {
                eventHandler(VerifyEvent.OnRetryCallClick)
            },
            text = stringResource(id = R.string.verify_call_again),
            style = Theme.fonts.bold
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    LaunchedEffect(key1 = viewState.error) {
        if (viewState.error == VerifyStepError.Common) {
            Toast.makeText(
                context,
                R_core.string.common_smth_wrong,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}