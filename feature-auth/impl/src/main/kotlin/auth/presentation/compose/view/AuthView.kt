package auth.presentation.compose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
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
            .padding(12.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(top = 48.dp)
                .size(48.dp),
            painter = painterResource(R.drawable.auth_phone_ic),
            contentDescription = "poster"
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.enter_phone),
            fontSize = 16.sp,
            color = Theme.colors.textPrimaryColor
        )
        CommonTextField(
            text = viewState.phone,
            hint = stringResource(id = R.string.phone_hint),
            enabled = !viewState.isLoading,
            onValueChanged = {
                eventHandler(AuthEvent.PhoneChanged(it))
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp)
        ) {
            Checkbox(
                checked = viewState.isAgreementChecked,
                onCheckedChange = {
                    eventHandler(AuthEvent.OnAgreementClick)
                })
            Column {
                Text(
                    text = stringResource(id = R.string.agreement),
                    fontSize = 16.sp,
                    color = Theme.colors.textPrimaryColor
                )
                Text(
                    modifier = Modifier.clickable {
                        eventHandler(AuthEvent.OnOfferCLick)
                    },
                    textDecoration = TextDecoration.Underline,
                    text = stringResource(id = R.string.read_offer),
                    fontSize = 16.sp,
                    color = Theme.colors.textPrimaryColor
                )
            }
        }
        val gradient =
            Brush.horizontalGradient(
                listOf(
                    Color(0xFF4A82D0),
                    Color(0xFF4B83CA),
                    Color(0xFF4D88B8),
                    Color(0xFF518E9B),
                    Color(0xFF579872),
                    Color(0xFF5CA14A),
                    Color(0xFF67A649),
                    Color(0xFF84B445),
                    Color(0xFFB4C93F),
                    Color(0xFFF0E537)
                )
            )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(gradient),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                onClick = { eventHandler(AuthEvent.OnEntranceButtonCLick) },
            ) {
                Text(
                    text = stringResource(id = R.string.entrance),
                    color = Theme.colors.textSecondaryColor,
                    fontSize = 20.sp
                )
            }
        }
    }
}