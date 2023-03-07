package neworder.root.presentation.compose.view

import CommonErrorScreen
import ScrollScreenActionButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modifiers.autoScrollInFocus
import neworder.root.presentation.viewmodel.model.NewOrderEvent
import neworder.root.presentation.viewmodel.model.NewOrderState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.LIMITS.Common.MAX_DESCRIPTION_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_CAPACITY_MAX_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_PALLETS_MAX_CHARS
import view.ExtrasTextField
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun NewOrderView(state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    val buttonHeight = remember {
        mutableStateOf(0f)
    }
    val scrollState = rememberScrollState()
    val modifier = Modifier.autoScrollInFocus(scrollState, buttonHeight)
    if (state.isError) {
        CommonErrorScreen { eventHandler(NewOrderEvent.OnRetryClick) }
    } else {
        Column(
            modifier = Modifier
                .padding(PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp))
                .verticalScroll(scrollState)
        ) {
            TitleView(eventHandler)
            if (state.isLoading) {
                NewOrderLoadingView()
            } else {
                MarketplaceItem(state)
                CargoTypeItem(state, eventHandler)
                BoxesItem(modifier, state, eventHandler)
                PalletsItem(modifier, state, eventHandler)
                WeightItem(modifier, state, eventHandler)
                AddressItem(state, eventHandler)
                DateItem(state, eventHandler)
                TimeItem(state, eventHandler)
                StorageItem(state, eventHandler)
                ExtrasTextField(
                    modifier = modifier,
                    text = state.extras.stateText
                ) {
                    eventHandler(NewOrderEvent.OnExtrasClick)
                }
                CommentItem(modifier, state, eventHandler)
            }
            CreateOrderButtonItem(
                state = state,
                onPositioned = {
                    buttonHeight.value = it
                },
                eventHandler = eventHandler
            )
        }
    }
}

@Composable
private fun TitleView(eventHandler: (NewOrderEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Icon(
            modifier = Modifier
                .clip(Theme.shapes.roundedButton)
                .clickable { eventHandler(NewOrderEvent.OnBackClick) },
            painter = painterResource(id = R_core.drawable.close_ic),
            contentDescription = null
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.new_order_title),
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
    }
}

@Composable
private fun MarketplaceItem(state: NewOrderState) {
    StandardTextField(
        title = stringResource(R.string.new_order_marketplace),
        state = state.marketplace,
        enabled = false,
        textStyle = Theme.fonts.regular.copy(
            color = Theme.colors.disabledTextColor
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            disabledIndicatorColor = Theme.colors.disabledTextColor
        )
    )
}

@Composable
private fun CargoTypeItem(state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = Modifier
            .clip(Theme.shapes.textFields)
            .clickable { eventHandler(NewOrderEvent.OnCargoTypeClick) },
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
}

@Composable
private fun BoxesItem(modifier: Modifier, state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.route_boxes_count),
        state = state.boxesCount,
        hint = stringResource(R.string.route_count_hint),
        discription = stringResource(id = R.string.new_order_box_description),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(NewOrderEvent.OnBoxesCountChanged(it)) }
}

@Composable
private fun PalletsItem(modifier: Modifier, state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.new_order_pallet_title),
        state = state.palletsCount,
        hint = stringResource(R.string.route_count_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CAR_PALLETS_MAX_CHARS
    ) { eventHandler(NewOrderEvent.OnPalletsCountChanged(it)) }
}

@Composable
private fun WeightItem(modifier: Modifier, state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = modifier,
        title = stringResource(R.string.route_weight),
        state = state.weight,
        hint = stringResource(R.string.new_order_weight_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(NewOrderEvent.OnWeightChanged(it)) }
}

@Composable
private fun AddressItem(state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = Modifier
            .clip(Theme.shapes.textFields)
            .clickable { eventHandler(NewOrderEvent.OnAddressClick) },
        title = stringResource(R.string.new_order_address_title),
        state = state.address,
        hint = stringResource(R_core.string.common_add_address),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
}

@Composable
private fun DateItem(state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = Modifier
            .clip(Theme.shapes.textFields)
            .clickable { eventHandler(NewOrderEvent.OnArrivalDateClick) },
        title = stringResource(R.string.new_order_date_title),
        state = state.arrivalDate,
        hint = stringResource(R.string.new_order_date_hint),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.new_order_date_ic),
                contentDescription = null
            )
        }
    )
}

@Composable
private fun TimeItem(state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = Modifier
            .clip(Theme.shapes.textFields)
            .clickable { eventHandler(NewOrderEvent.OnArrivalTimeClick) },
        title = stringResource(R.string.route_delivery_time),
        state = state.arrivalTime,
        hint = stringResource(R_core.string.common_choose),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
}

@Composable
private fun StorageItem(state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = Modifier
            .clip(Theme.shapes.textFields)
            .clickable { eventHandler(NewOrderEvent.OnStorageClick) },
        title = stringResource(R.string.common_delivery_adddress),
        state = state.storage,
        hint = stringResource(R_core.string.common_choose),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
}

@Composable
private fun CommentItem(modifier: Modifier, state: NewOrderState, eventHandler: (NewOrderEvent) -> Unit) {
    StandardTextField(
        modifier = modifier.then(
            Modifier
                .padding(bottom = 100.dp)
                .defaultMinSize(minHeight = 90.dp)
        ),
        title = stringResource(R_core.string.common_info_comment),
        state = state.comment,
        hint = stringResource(R.string.new_order_comment_hint),
        maxLines = Int.MAX_VALUE,
        maxChar = MAX_DESCRIPTION_CHARS
    ) { eventHandler(NewOrderEvent.OnCommentChanged(it)) }
}

@Composable
private fun CreateOrderButtonItem(
    state: NewOrderState,
    onPositioned: (Float) -> Unit,
    eventHandler: (NewOrderEvent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            textRes = R.string.new_order_create,
            onPositioned = onPositioned,
            additionalText = state.createButton.subtitle,
            enabled = state.createButton.isEnabled,
            height = 65.dp,
            padding = PaddingValues(16.dp)
        ) { eventHandler(NewOrderEvent.OnCreateButtonClick) }
    }
}