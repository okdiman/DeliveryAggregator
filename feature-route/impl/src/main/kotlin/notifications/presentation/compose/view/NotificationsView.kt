package notifications.presentation.compose.view

import ActionButton
import CommonErrorScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import notifications.domain.model.RouteNotificationsStatus
import notifications.presentation.compose.model.NotificationAssignedRequestUiModel
import notifications.presentation.compose.model.NotificationUiModel
import notifications.presentation.viewmodel.model.NotificationsEvent
import notifications.presentation.viewmodel.model.NotificationsState
import root.domain.model.status.OrderStatusProgress
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BackButton
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun NotificationsView(
    state: NotificationsState,
    eventHandler: (NotificationsEvent) -> Unit,
) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(NotificationsEvent.OnRetryClick) }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            item {
                NotificationsTitle(eventHandler)
            }
            when {
                state.isLoading -> {
                    items(2) {
                        NotificationLoadingView()
                    }
                }
                else -> {
                    items(state.notifications) { uiModel ->
                        NotificationItemView(
                            model = uiModel,
                            eventHandler = eventHandler
                        ) {
                            NotificationItemContent(uiModel, eventHandler)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationsTitle(eventHandler: (NotificationsEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        BackButton { eventHandler(NotificationsEvent.OnBackClick) }
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
private fun NotificationItemView(
    model: NotificationUiModel,
    eventHandler: (NotificationsEvent) -> Unit,
    content: @Composable () -> Unit,
) {
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
            .clickable {
                eventHandler(NotificationsEvent.OnNotificationClick(model.notificationId))
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                model.imageRes?.let { imageRes ->
                    Image(painter = painterResource(id = imageRes), contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }
    }
}

@Composable
private fun NotificationItemContent(model: NotificationUiModel, eventHandler: (NotificationsEvent) -> Unit) {
    Text(
        modifier = Modifier.padding(vertical = 2.dp),
        text = model.text,
        style = Theme.fonts.regular
    )

    if (model is NotificationAssignedRequestUiModel) {
        Spacer(modifier = Modifier.height(16.dp))
        NotificationAssignedDetails(model)
    }

    if (model.status == RouteNotificationsStatus.CHANGED && model.orderStatus == OrderStatusProgress.CHANGED) {
        Spacer(modifier = Modifier.height(10.dp))
        ActionButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            textRes = R.string.order_changes_see,
            height = 36.dp,
            fontSize = 16.sp,
            padding = PaddingValues(vertical = 16.dp)
        ) {
            model.routeId?.let { id ->
                eventHandler(NotificationsEvent.OnSeeChangesClick(id))
            }
        }
    }
}

@Composable
private fun NotificationAssignedDetails(
    model: NotificationAssignedRequestUiModel,
) {
    NotificationAssignDetailItem(stringResource(R.string.common_contractor), model.fullName)
    NotificationAssignDetailItem(stringResource(R.string.common_car_plate), model.carPlate)
    NotificationAssignDetailItem(stringResource(R.string.common_car_model), model.carModel)
    NotificationAssignDetailItem(stringResource(R.string.common_phone), model.phone)
    NotificationAssignDetailItem(stringResource(R.string.common_delivery_date), model.arrivalDay)
    NotificationAssignDetailItem(stringResource(R.string.common_delivery_time_interval), model.arrivalTime)
}

@Composable
private fun NotificationAssignDetailItem(title: String, subtitle: String) {
    Text(text = title, color = Theme.colors.textFourthColor, style = Theme.fonts.regular)
    Spacer(modifier = Modifier.height(6.dp))
    Text(text = subtitle, style = Theme.fonts.regular)
    Spacer(modifier = Modifier.height(12.dp))
}
