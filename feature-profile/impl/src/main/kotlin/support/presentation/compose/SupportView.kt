package support.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import support.presentation.viewmodel.model.SupportEvent
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl.R

@Composable
fun SupportView(eventHandler: (SupportEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 40.dp)
    ) {
        ActionButton(
            textRes = R.string.call,
            alignment = Alignment.Center,
            padding = PaddingValues(0.dp)
        ) { eventHandler(SupportEvent.OnCallClick) }
        ActionButton(
            textRes = R.string.send_email,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 16.dp)
        ) { eventHandler(SupportEvent.OnEmailClick) }
        ActionButton(
            textRes = R.string.reject,
            textColor = Theme.colors.textPrimaryColor,
            color = Theme.colors.disabledButtonColor,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 16.dp, bottom = 4.dp)
        ) { eventHandler(SupportEvent.OnBackClick) }
    }
}