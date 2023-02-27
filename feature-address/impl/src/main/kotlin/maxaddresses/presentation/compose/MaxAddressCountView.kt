package maxaddresses.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import maxaddresses.presentation.viewmodel.model.MaxAddressCountEvent
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_address.impl.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun MaxAddressCountView(eventHandler: (MaxAddressCountEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.max_address), style = Theme.fonts.bold.copy(
                fontSize = 20.sp
            )
        )
        ActionButton(
            textRes = R_core.string.common_good,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 24.dp)
        ) { eventHandler(MaxAddressCountEvent.OnBackClick) }
    }
}