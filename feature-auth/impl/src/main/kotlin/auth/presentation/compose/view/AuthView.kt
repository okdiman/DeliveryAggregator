package auth.presentation.compose.view

import ActionButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import auth.presentation.viewmodel.model.AuthEvent
import auth.presentation.viewmodel.model.AuthState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_auth.impl.R
import view.CommonTextField

@Composable
fun AuthView(viewState: AuthState, eventHandler: (AuthEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp, end = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(48.dp),
            painter = painterResource(R.drawable.auth_phone_ic),
            contentDescription = "poster"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.enter_phone),
            style = Theme.fonts.regular.copy(
                fontSize = 16.sp,
                color = Theme.colors.textPrimaryColor
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = stringResource(id = R.string.phone_prefix),
                style = Theme.fonts.bold.copy(
                    fontSize = 24.sp,
                    color = Theme.colors.textPrimaryColor
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            CommonTextField(
                text = viewState.phone,
                textStyle = Theme.fonts.bold.copy(fontSize = 24.sp),
                hint = stringResource(id = R.string.phone_hint),
                enabled = !viewState.isLoading,
                onValueChanged = {
                    eventHandler(AuthEvent.PhoneChanged(it))
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isPhone = true,
                maxChar = 10
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 4.dp)
                .fillMaxWidth()
        ) {
            Checkbox(
                checked = viewState.isAgreementChecked,
                colors = CheckboxDefaults.colors(
                    checkedColor = Theme.colors.textPrimaryColor
                ),
                onCheckedChange = {
                    eventHandler(AuthEvent.OnAgreementClick)
                })
            Column {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.agreement),
                    style = Theme.fonts.regular.copy(
                        fontSize = 16.sp,
                        color = Theme.colors.textPrimaryColor
                    )
                )
                Text(
                    modifier = Modifier.clickable {
                        eventHandler(AuthEvent.OnOfferCLick)
                    },
                    text = stringResource(id = R.string.read_offer),
                    style = Theme.fonts.regular.copy(
                        fontSize = 16.sp,
                        color = Theme.colors.textPrimaryColor,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ActionButton(
                text = stringResource(id = R.string.entrance),
                enabled = viewState.isButtonEnabled,
                gradient = Theme.gradients.actionButtonGradient
            ) {
                eventHandler(AuthEvent.OnEntranceButtonCLick)
            }
        }
    }
}

@Preview
@Composable
fun AuthView_Preview() {
    AuthView(viewState = AuthState(), eventHandler = {})
}