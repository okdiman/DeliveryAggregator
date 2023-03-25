package deleteorder.presentation.compose

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
import deleteorder.presentation.viewmodel.model.DeleteOrderEvent
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun DeleteOrderView(eventHandler: (DeleteOrderEvent) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 40.dp)) {
        Text(
            text = stringResource(id = R.string.delete_order_title),
            style = Theme.fonts.bold.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        ActionButton(
            textRes = R_core.string.common_reject,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 24.dp)
        ) { eventHandler(DeleteOrderEvent.OnConfirmClick) }
        ActionButton(
            textRes = R.string.cancellation_order_no_reject,
            textColor = Theme.colors.textPrimaryColor,
            color = Theme.colors.disabledButtonColor,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 16.dp)
        ) { eventHandler(DeleteOrderEvent.OnCancelClick) }
    }
}