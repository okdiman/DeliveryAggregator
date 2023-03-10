package orderdetails.loadingstate.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modifiers.autoScrollInFocus
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
import view.ExtrasTextField
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun OrderLoadingView(state: OrderLoadingState, eventHandler: (OrderLoadingEvent) -> Unit) {
    val buttonHeight = remember {
        mutableStateOf(0f)
    }
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        state = scrollState
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
            OrderLoadingTextFieldBlock(
                modifier = Modifier.autoScrollInFocus(scrollState, buttonHeight),
                state = state,
                eventHandler = eventHandler
            )
        }
    }
    if (state.isDoneButtonVisible) {
        OrderStateDoneButton(
            onPositioned = { buttonHeight.value = it }
        ) { eventHandler(OrderLoadingEvent.OnDoneButtonClick) }
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
    modifier: Modifier,
    state: OrderLoadingState,
    eventHandler: (OrderLoadingEvent) -> Unit
) {
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.loading_boxes_count),
        state = state.boxesCount,
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        hint = stringResource(R.string.route_count_hint),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(OrderLoadingEvent.OnBoxesCountChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.loading_pallets_count),
        state = state.palletsCount,
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        hint = stringResource(R.string.route_count_hint),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(OrderLoadingEvent.OnPalletsCountChanged(it)) }
    StandardTextField(
        modifier = Modifier.clickable {
            eventHandler(OrderLoadingEvent.OnOpenCargoTypeBSClick)
        },
        title = stringResource(R.string.route_cargo_type),
        state = state.cargoType,
        hint = stringResource(R_core.string.common_choose),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
    ExtrasTextField(modifier = modifier, text = state.extras.stateText) {
        eventHandler(OrderLoadingEvent.OnOpenExtrasBSClick)
    }
    Spacer(modifier = Modifier.height(if (state.isDoneButtonVisible) 120.dp else 16.dp))
}