package notifications.root.presentation.compose

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
import notifications.root.presentation.compose.model.NotificationUiModel
import notifications.root.presentation.viewmodel.model.NotificationsEvent
import notifications.root.presentation.viewmodel.model.NotificationsState
import theme.Theme
import view.BackButton
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun NotificationsView(
    state: NotificationsState,
    eventHandler: (NotificationsEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 4.dp,
                start = 16.dp,
                end = 16.dp
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
    Box(modifier = Modifier.fillMaxWidth()) {
        BackButton { eventHandler(NotificationsEvent.OnBackCLick) }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R_core.string.notifications_title),
            style = Theme.fonts.bold.copy(
                fontSize = 20.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun NotificationItemView(model: NotificationUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .advancedShadow(
                alpha = 0.1f,
                cornersRadius = 20.dp,
                shadowBlurRadius = 20.dp
            )
            .clip(Theme.shapes.bigCard)
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
            if (model.imageRes != null) {
                Image(painter = painterResource(id = model.imageRes), contentDescription = null)
            }
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                text = model.text.toString(),
                style = Theme.fonts.regular
            )
        }
    }
}