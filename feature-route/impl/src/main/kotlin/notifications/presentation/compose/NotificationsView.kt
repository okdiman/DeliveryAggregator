package notifications.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modifiers.advancedShadow
import notifications.domain.NotificationType
import notifications.presentation.compose.model.NotificationUiModel
import notifications.presentation.viewmodel.model.NotificationsEvent
import notifications.presentation.viewmodel.model.NotificationsState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_route.impl.R
import view.BackButton
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
fun NotificationsView(state: NotificationsState, eventHandler: (NotificationsEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 4.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
    ) {
        item {
            NotificationsTitle(eventHandler)
        }
        items(state.notifications) { uiModel ->
            NotificationItemView(uiModel)
        }
    }
}

@Composable
private fun NotificationsTitle(eventHandler: (NotificationsEvent) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        BackButton { eventHandler(NotificationsEvent.OnBackCLick) }
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.notifications_title),
            style = Theme.fonts.bold.copy(
                fontSize = 20.sp
            )
        )
        Box(
            modifier = Modifier.clip(Theme.shapes.textFields),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.notifications_delete),
                style = Theme.fonts.regular
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun NotificationItemView(model: NotificationUiModel) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .advancedShadow(
                alpha = 0.1f,
                cornersRadius = 40.dp,
                shadowBlurRadius = 40.dp
            )
            .clip(Theme.shapes.roundedButton)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 16.dp
                )
        ) {
            Image(painter = painterResource(id = model.imageRes), contentDescription = null)
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                text = model.text,
                style = Theme.fonts.regular
            )
            if (model.type == NotificationType.Cancelled) {
                IconButton(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = {}) {
                    Icon(
                        painter = painterResource(id = R_core.drawable.close_ic),
                        contentDescription = null
                    )
                }
            }
        }
    }
}