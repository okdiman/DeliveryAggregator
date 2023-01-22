package orderdetails.presentation.compose

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
import orderdetails.presentation.viewmodel.model.OrderDetailsEvent
import orderdetails.presentation.viewmodel.model.OrderDetailsState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.Helpers.NUMBER
import view.BackButton

@Composable
internal fun OrderDetailsView(
    state: OrderDetailsState, eventHandler: (OrderDetailsEvent) -> Unit
) {
    when {
        state.isLoading -> {

        }
        state.isError -> {
            CommonErrorScreen { eventHandler(OrderDetailsEvent.OnRetryClick) }
        }
        else -> {
            val verticalScroll = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScroll)
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    BackButton(modifier = Modifier.padding(top = 3.dp)) {
                        eventHandler(
                            OrderDetailsEvent.OnBackClick
                        )
                    }
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = buildString { append(NUMBER + state.uiModel.id) },
                        style = Theme.fonts.bold.copy(fontSize = 20.sp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
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
                    ) { //FIXME !!!index
                        Text(text = "1", style = Theme.fonts.regular)
                    }
                }
                OrderDetailsRow(
                    title = stringResource(id = R.string.order_details_delivery_date),
                    info = state.uiModel.deliveryDate
                )
                OrderDetailsRow(
                    title = stringResource(id = R.string.order_details_delivery_time),
                    info = state.uiModel.deliveryTime
                )
                OrderDetailsRow(
                    title = stringResource(id = R.string.order_details_pallet_count),
                    info = state.uiModel.palletCount
                )
                OrderDetailsRow(
                    title = stringResource(id = R.string.order_details_delivery_address),
                    info = state.uiModel.deliveryAddress
                )
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
                OrderDetailsStatusCard(
                    title = stringResource(id = R.string.order_details_loading_status),
                    isCompleted = true
                ) { eventHandler(OrderDetailsEvent.OnLoadingStateClick) }
                Spacer(modifier = Modifier.height(8.dp))
                OrderDetailsStatusCard(
                    title = stringResource(id = R.string.order_details_delivery_status),
                    isCompleted = false
                ) { eventHandler(OrderDetailsEvent.OnDeliveryStateClick) }
            }
        }
    }
}

@Composable
private fun OrderDetailsRow(title: String, info: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = Theme.fonts.regular.copy(color = Theme.colors.textPrimaryColor.copy(alpha = 0.7f))
        )
        Text(modifier = Modifier.padding(start = 8.dp), text = info, style = Theme.fonts.regular)
    }
}

@Composable
private fun OrderDetailsStatusCard(title: String, isCompleted: Boolean, onClick: () -> Unit) {
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
            .clickable { onClick() })
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = Theme.fonts.bold)
            if (isCompleted) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(id = R.string.order_details_delivery_status_completed),
                    style = Theme.fonts.regular
                )
            }
        }
    }
}