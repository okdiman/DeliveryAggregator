package view

import ActionButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import trinity_monsters.delivery_aggregator.core_ui.R

@Composable
fun ShouldPermissionRationaleBSScreen(
    text: String,
    onAcceptClick: () -> Unit,
    onDeclineClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 40.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = Theme.fonts.bold.copy(
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center
        )
        ActionButton(
            textRes = R.string.notifications_accept_permission,
            textColor = Theme.colors.textSecondaryColor,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 24.dp)
        ) { onAcceptClick() }
        ActionButton(
            textRes = R.string.notifications_decline_permission,
            textColor = Theme.colors.textPrimaryColor,
            color = Theme.colors.disabledButtonColor,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 16.dp)
        ) { onDeclineClick() }
    }
}