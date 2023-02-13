package orderdetails.loadingstate.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orderdetails.loadingstate.presentation.compose.view.OrderPhotoHintView
import orderdetails.loadingstate.presentation.compose.view.OrderPhotoPlaceholder
import orderdetails.loadingstate.presentation.compose.view.OrderPhotoView
import orderdetails.loadingstate.presentation.compose.view.OrderStateDoneButton
import orderdetails.loadingstate.presentation.compose.view.OrderStateTitle
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingEvent
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.LIMITS.Transport.CAR_CAPACITY_MAX_CHARS
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun OrderLoadingView(state: OrderLoadingState, eventHandler: (OrderLoadingEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            OrderStateTitle(stringResource(R.string.loading)) {
                eventHandler(OrderLoadingEvent.OnBackClick)
            }
        }
        item {
            OrderPhotoHintView()
        }
        item {
            if (state.photo == null) {
                OrderPhotoPlaceholder(
                    permissionState = state.permissionState,
                    onPhotoClick = { eventHandler(OrderLoadingEvent.OnPhotoClick) },
                    onPhotoAdded = { eventHandler(OrderLoadingEvent.OnPhotoAdded(it)) }
                )
            } else {
                OrderPhotoView(state.photo.uri, state.photo.date, state.photo.isLoading)
            }
        }
        item {
            OrderLoadingSpecifyInfoView()
        }
        item {
            OrderLoadingTextFieldBlock(state, eventHandler)
        }
    }
    if (state.isDoneButtonVisible) {
        OrderStateDoneButton { eventHandler(OrderLoadingEvent.OnDoneButtonClick) }
    }
}

@Composable
private fun OrderLoadingSpecifyInfoView() {
    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = stringResource(id = R.string.loading_specify_info),
        style = Theme.fonts.bold.copy(
            fontSize = 20.sp
        )
    )
}

@Composable
private fun OrderLoadingTextFieldBlock(
    state: OrderLoadingState,
    eventHandler: (OrderLoadingEvent) -> Unit
) {
    StandardTextField(
        title = stringResource(R.string.loading_boxes_count),
        state = state.boxesCount,
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        hint = stringResource(R.string.loading_count_hint),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(OrderLoadingEvent.OnBoxesCountChanged(it)) }
    StandardTextField(
        title = stringResource(R.string.loading_pallets_count),
        state = state.palletsCount,
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        hint = stringResource(R.string.loading_count_hint),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(OrderLoadingEvent.OnPalletsCountChanged(it)) }
    StandardTextField(
        modifier = Modifier.clickable {
            eventHandler(OrderLoadingEvent.OnOpenCargoTypeBSClick)
        },
        title = stringResource(R.string.loading_cargo_type),
        state = state.cargoType,
        hint = stringResource(R.string.loading_choose),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = trinity_monsters.delivery_aggregator.core_ui.R.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
    StandardTextField(
        modifier = Modifier.clickable {
            eventHandler(OrderLoadingEvent.OnOpenAdditionalOptBSClick)
        },
        title = stringResource(R.string.loading_add_info),
        state = state.additionalOptions,
        hint = stringResource(R.string.loading_choose),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
    if (state.isDoneButtonVisible) {
        Spacer(modifier = Modifier.height(120.dp))
    }
}