package departure.maxaddresses.presentation.compose

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
import departure.maxaddresses.presentation.viewmodel.model.DepartureMaxAddressCountEvent
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl.R

@Composable
fun DepartureMaxAddressCountView(eventHandler: (DepartureMaxAddressCountEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.departure_max_address), style = Theme.fonts.bold.copy(
                fontSize = 20.sp
            )
        )
        ActionButton(
            textRes = R.string.departure_good,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 24.dp)
        ) { eventHandler(DepartureMaxAddressCountEvent.OnBackClick) }
    }
}