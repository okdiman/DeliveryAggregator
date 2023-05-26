package orderdetails.root.presentation.compose.view

import ActionButton
import CommonErrorScreen
import ScrollScreenActionButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orderdetails.root.presentation.viewmodel.model.OrderDetailsEvent
import orderdetails.root.presentation.viewmodel.model.OrderDetailsState
import root.domain.model.status.OrderStatusProgress
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.UiConstants.SCROLL_SCREEN_ACTION_BUTTON_OCCUPIED_HEIGHT
import utils.ext.toStringWithEnding

@Composable
internal fun OrderDetailsView(state: OrderDetailsState, eventHandler: (OrderDetailsEvent) -> Unit) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(OrderDetailsEvent.OnRetryClick) }
    } else {
        val verticalScroll = rememberScrollState()
        Box(modifier = Modifier.fillMaxSize()) {
            val isPayButtonVisible = state.uiModel.canPay
            val isDeleteButtonVisible = state.uiModel.status == OrderStatusProgress.CREATED

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScroll)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = if (isPayButtonVisible) SCROLL_SCREEN_ACTION_BUTTON_OCCUPIED_HEIGHT.dp else 0.dp
                    )
            ) {
                OrderDetailsTitleView(state) {
                    eventHandler(OrderDetailsEvent.OnBackClick)
                }
                if (state.isLoading) {
                    OrderDetailsLoadingView()
                } else {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.order_details),
                        style = Theme.fonts.bold.copy(fontSize = 24.sp)
                    )
                    state.uiModel.contractorName?.let { name ->
                        OrderDetailsRowView(
                            title = stringResource(id = R.string.order_details_contractor_name),
                            info = name
                        )
                    }
                    state.uiModel.contractorPhone?.let { phone ->
                        OrderDetailsRowView(
                            title = stringResource(id = R.string.order_details_client_phone),
                            info = phone
                        )
                    }
                    state.uiModel.deliveryDate?.let { deliveryDate ->
                        OrderDetailsRowView(
                            title = stringResource(id = R.string.order_details_delivery_date),
                            info = deliveryDate
                        )
                    }
                    OrderDetailsRowView(
                        title = stringResource(id = R.string.route_delivery_time),
                        info = state.uiModel.arrivalTime
                    )
                    OrderDetailsRowView(
                        title = stringResource(id = R.string.order_details_pallet_count),
                        info = state.uiModel.palletCount
                    )
                    OrderDetailsRowView(
                        title = stringResource(id = R.string.order_details_delivery_address),
                        info = state.uiModel.deliveryAddress
                    )
                    OrderDetailsAdditionalInfoView { eventHandler(OrderDetailsEvent.OnAdditionalInfoClick) }
                    OrderDetailsStatusView(
                        state = state,
                        showDescription = !state.uiModel.isLoadedOrDelivered,
                        eventHandler = eventHandler
                    )
                }
            }

            if (isPayButtonVisible) {
                PayButtonView(state, eventHandler)
            }
            if (isDeleteButtonVisible) {
                DeleteButtonView(eventHandler)
            }
        }
    }
}

@Composable
private fun OrderDetailsStatusView(
    state: OrderDetailsState,
    showDescription: Boolean = false,
    eventHandler: (OrderDetailsEvent) -> Unit,
) {
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = stringResource(id = R.string.order_details_status),
        style = Theme.fonts.bold.copy(
            fontSize = 20.sp
        )
    )
    if (showDescription) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.order_details_status_description),
            style = Theme.fonts.regular,
            color = Theme.colors.darkLabelColor
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
    if (state.uiModel.load?.imageUrl != null) {
        Spacer(modifier = Modifier.height(24.dp))
        OrderPhotoView(
            title = stringResource(id = R.string.order_details_loading_step),
            uri = state.uiModel.load.imageUrl,
            date = state.uiModel.load.loadDateTime,
            onClick = { eventHandler(OrderDetailsEvent.OnOrderPhotoClick(state.uiModel.load.imageUrl)) }
        )
    }
    if (state.uiModel.delivery?.imageUrl != null) {
        Spacer(modifier = Modifier.height(10.dp))
        OrderPhotoView(
            title = stringResource(id = R.string.order_details_delivery_step),
            uri = state.uiModel.delivery.imageUrl,
            date = state.uiModel.delivery.deliveryDateTime,
            onClick = { eventHandler(OrderDetailsEvent.OnOrderPhotoClick(state.uiModel.delivery.imageUrl)) }
        )
    }
}

@Composable
private fun PayButtonView(
    state: OrderDetailsState,
    eventHandler: (OrderDetailsEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            text = stringResource(R.string.order_details_pay, state.uiModel.price.toStringWithEnding()),
            enabled = !state.uiModel.isPaid && !state.isLoading && !state.isError,
            alignment = Alignment.BottomCenter,
            padding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
        ) { eventHandler(OrderDetailsEvent.OnPayClick) }
    }
}

@Composable
private fun DeleteButtonView(eventHandler: (OrderDetailsEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        ActionButton(
            textRes = R.string.order_details_delete,
            color = Theme.colors.textFiveColor,
            textColor = Theme.colors.textPrimaryColor,
            alignment = Alignment.BottomCenter,
            padding = PaddingValues(top = 16.dp, bottom = 24.dp)
        ) { eventHandler(OrderDetailsEvent.OnDeleteClick) }
    }
}