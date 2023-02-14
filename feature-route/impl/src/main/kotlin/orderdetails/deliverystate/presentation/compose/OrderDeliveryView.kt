package orderdetails.deliverystate.presentation.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import orderdetails.deliverystate.presentation.viewmodel.model.OrderDeliveryEvent
import orderdetails.deliverystate.presentation.viewmodel.model.OrderDeliveryState
import orderdetails.loadingstate.presentation.compose.view.OrderPhotoHintView
import orderdetails.loadingstate.presentation.compose.view.OrderPhotoPlaceholder
import orderdetails.loadingstate.presentation.compose.view.OrderPhotoView
import orderdetails.loadingstate.presentation.compose.view.OrderStateDoneButton
import orderdetails.loadingstate.presentation.compose.view.OrderStateTitle
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.CheckboxView
import view.StandardTextField

@Composable
internal fun OrderDeliveryView(state: OrderDeliveryState, eventHandler: (OrderDeliveryEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            OrderStateTitle(stringResource(R.string.delivery_state_title)) {
                eventHandler(OrderDeliveryEvent.OnBackClick)
            }
        }
        item {
            OrderPhotoHintView()
        }
        item {
            if (state.photo == null) {
                OrderPhotoPlaceholder(
                    permissionState = state.permissionState,
                    onPhotoClick = { eventHandler(OrderDeliveryEvent.OnPhotoClick) },
                    onPhotoAdded = { eventHandler(OrderDeliveryEvent.OnPhotoAdded(it)) }
                )
            } else {
                OrderPhotoView(state.photo.uri, state.photo.date, state.photo.isLoading)
            }
        }
        item {
            StandardTextField(
                modifier = Modifier.defaultMinSize(minHeight = 90.dp),
                title = stringResource(R.string.delivery_note_title),
                state = state.comment,
                hint = stringResource(R.string.delivery_note_hint),
            ) { eventHandler(OrderDeliveryEvent.OnCommentChanged(it)) }
        }
        item {
            OrderDeliveryCheckboxView(state, eventHandler)
        }
    }
    if (state.isDoneButtonVisible) {
        OrderStateDoneButton { eventHandler(OrderDeliveryEvent.OnDoneButtonClick) }
    }
}

@Composable
private fun OrderDeliveryCheckboxView(
    state: OrderDeliveryState,
    eventHandler: (OrderDeliveryEvent) -> Unit
) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Theme.shapes.textFields)
            .selectable(
                selected = state.isProblem,
                onClick = {
                    eventHandler(OrderDeliveryEvent.OnProblemStateChanged(!state.isProblem))
                }
            )
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CheckboxView(
            checked = state.isProblem,
            colors = CheckboxDefaults.colors(
                checkedColor = Theme.colors.textPrimaryColor
            )
        )
        Text(
            modifier = Modifier.padding(horizontal = 6.dp),
            text = stringResource(id = R.string.delivery_problems),
            style = Theme.fonts.regular
        )
    }
    if (state.isDoneButtonVisible) {
        Spacer(modifier = Modifier.height(120.dp))
    }
}