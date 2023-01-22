package exit.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import exit.presentation.viewmodel.model.ExitEvent
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_profile.impl.R

@Composable
internal fun ExitView(eventHandler: (ExitEvent) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 36.dp)) {
        Text(
            text = stringResource(id = R.string.exit_are_u_sure),
            style = Theme.fonts.bold.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        ActionButton(
            textRes = R.string.exit,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 24.dp)
        ) { eventHandler(ExitEvent.OnConfirmClick) }
        ActionButton(
            textRes = R.string.exit_stay,
            textColor = Theme.colors.textPrimaryColor,
            color = Theme.colors.disabledButtonColor,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 16.dp)
        ) { eventHandler(ExitEvent.OnStayClick) }
    }
}