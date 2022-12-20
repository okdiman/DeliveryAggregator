package root.presentation

import ActionButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R

@Composable
fun RegistrationButton(isEnabled: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 44.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ActionButton(
            enabled = isEnabled,
            text = stringResource(R.string.continue_button),
            onClick = { onClick() }
        )
    }
}