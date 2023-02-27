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
import trinity_monsters.delivery_aggregator.feature_profile.impl.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun SupportView(eventHandler: (SupportEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 36.dp)
    ) {
        ActionButton(
            textRes = R.string.support_call,
            alignment = Alignment.Center,
            padding = PaddingValues(0.dp)
        ) { eventHandler(SupportEvent.OnCallClick) }
        ActionButton(
            textRes = R.string.support_send_email,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 16.dp)
        ) { eventHandler(SupportEvent.OnEmailClick) }
        ActionButton(
            textRes = R_core.string.common_reject,
            textColor = Theme.colors.textPrimaryColor,
            color = Theme.colors.disabledButtonColor,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 16.dp, bottom = 4.dp)
        ) { eventHandler(SupportEvent.OnBackClick) }
    }
}