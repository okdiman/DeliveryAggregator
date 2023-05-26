package orderdetails.root.presentation.compose.view

import CommonErrorScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modifiers.advancedShadow
import orderdetails.root.presentation.viewmodel.model.OrderDetailsEvent
import orderdetails.root.presentation.viewmodel.model.OrderDetailsState
import root.domain.model.status.OrderStatusProgress
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BackButtonView

@Composable
internal fun OrderDetailsView(
    state: OrderDetailsState, eventHandler: (OrderDetailsEvent) -> Unit
) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(OrderDetailsEvent.OnRetryClick) }
    } else {
        val verticalScroll = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScroll)
                .padding(horizontal = 16.dp)
        ) {
            OrderDetailsTitleView(state, eventHandler)
            if (state.isLoading) {
                OrderDetailsLoadingView()
            } else {
                OrderDetailsSubtitleView(state)
                OrderDetailsRowView(
                    title = stringResource(id = R.string.order_details_client_name),
                    info = state.uiModel.clientName
                )
                OrderDetailsRowView(
                    title = stringResource(id = R.string.order_details_client_phone),
                    info = state.uiModel.clientPhone
                )
                OrderDetailsRowView(
                    title = stringResource(id = R.string.order_details_delivery_date),
                    info = state.uiModel.deliveryDate
                )
                OrderDetailsRowView(
                    title = stringResource(id = R.string.route_delivery_time),
                    info = state.uiModel.deliveryTime
                )
                OrderDetailsRowView(
                    title = stringResource(id = R.string.order_details_pallet_count),
                    info = state.uiModel.palletCount
                )
                OrderDetailsRowView(
                    title = stringResource(id = R.string.order_details_delivery_address),
                    info = state.uiModel.deliveryAddress
                )
                OrderDetailsAdditionalInfoView(eventHandler)
                OrderDetailsStatusView(state, eventHandler)
            }
        }
    }
}

@Composable
private fun OrderDetailsTitleView(
    state: OrderDetailsState,
    eventHandler: (OrderDetailsEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        BackButtonView { eventHandler(OrderDetailsEvent.OnBackClick) }
        if (!state.isLoading) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = buildString {
                    append(stringResource(id = R.string.common_order_number) + state.uiModel.id)
                },
                style = Theme.fonts.bold.copy(fontSize = 20.sp)
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun OrderDetailsSubtitleView(state: OrderDetailsState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.order_details),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(34.dp)
                .clip(Theme.shapes.roundedButton)
                .background(Theme.colors.hintBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(text = state.uiModel.index, style = Theme.fonts.regular)
        }
    }
}

@Composable
private fun OrderDetailsAdditionalInfoView(eventHandler: (OrderDetailsEvent) -> Unit) {
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Theme.shapes.roundedButton)
            .background(Theme.colors.disabledButtonColor)
            .clickable { eventHandler(OrderDetailsEvent.OnAdditionalInfoClick) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = stringResource(id = R.string.order_details_additional_info),
            style = Theme.fonts.bold
        )
    }
}

@Composable
private fun OrderDetailsStatusView(
    state: OrderDetailsState,
    eventHandler: (OrderDetailsEvent) -> Unit
) {
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = stringResource(id = R.string.order_details_status),
        style = Theme.fonts.bold.copy(
            fontSize = 20.sp
        )
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = stringResource(id = R.string.order_details_status_info),
        style = Theme.fonts.regular
    )
    Spacer(modifier = Modifier.height(20.dp))
    OrderDetailsStatusCardView(
        title = stringResource(id = R.string.order_details_loading_status),
        isEnabled = state.uiModel.status == OrderStatusProgress.ASSIGNED,
        isCompleted = state.uiModel.status == OrderStatusProgress.DELIVERY ||
            state.uiModel.status == OrderStatusProgress.DONE,
        isWaiting = state.uiModel.status == OrderStatusProgress.CHANGED
    ) { eventHandler(OrderDetailsEvent.OnLoadingStateClick) }
    Spacer(modifier = Modifier.height(8.dp))
    OrderDetailsStatusCardView(
        title = stringResource(id = R.string.order_details_delivery_status),
        isEnabled = state.uiModel.status == OrderStatusProgress.DELIVERY,
        isCompleted = state.uiModel.status == OrderStatusProgress.DONE
    ) { eventHandler(OrderDetailsEvent.OnDeliveryStateClick) }
}

@Composable
private fun OrderDetailsStatusCardView(
    title: String,
    isEnabled: Boolean,
    isCompleted: Boolean = !isEnabled,
    isWaiting: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .advancedShadow(
                alpha = 0.1f,
                cornersRadius = 16.dp,
                shadowBlurRadius = 16.dp
            )
            .clip(Theme.shapes.card)
            .background(Color.White)
            .clickable {
                if (isEnabled && !isCompleted) {
                    onClick()
                }
            })
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = Theme.fonts.bold)
            if (isCompleted || isWaiting) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(
                        id = if (isCompleted) {
                            R.string.order_details_delivery_status_completed
                        } else {
                            R.string.order_details_delivery_status_waiting
                        }
                    ),
                    style = Theme.fonts.regular.copy(
                        color = Theme.colors.textPrimaryColor.copy(alpha = 0.7f)
                    )
                )
            }
        }
    }
}