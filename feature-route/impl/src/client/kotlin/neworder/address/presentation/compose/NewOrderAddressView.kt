package neworder.address.presentation.compose

import ActionButton
import CommonErrorScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import neworder.address.presentation.viewmodel.model.NewOrderAddressEvent
import neworder.address.presentation.viewmodel.model.NewOrderAddressState
import presentation.AddressView
import presentation.model.AddressUiModel
import root.presentation.AddressLoadingView
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BSTitleView
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun NewOrderAddressView(state: NewOrderAddressState, eventHandler: (NewOrderAddressEvent) -> Unit) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(NewOrderAddressEvent.OnRetryClick) }
    } else {
        val startState = remember { MutableTransitionState(false) }.also {
            it.targetState = true
        }
        AnimatedVisibility(visibleState = startState, enter = slideInVertically()) {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        PaddingValues(horizontal = 8.dp, vertical = 16.dp)
                    )
            ) {
                item {
                    BSTitleView(title = stringResource(id = R.string.new_order_adresses))
                }
                if (state.isLoading) {
                    item {
                        AddressLoadingView()
                    }
                } else {
                    items(state.addresses) {
                        DepartureAddressItemView(it, eventHandler)
                    }
                    item {
                        DepartureAddNewAddressView(eventHandler)
                    }
                    item {
                        ActionButton(
                            textRes = R_core.string.common_complete,
                            alignment = Alignment.Center,
                            padding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
                        ) { eventHandler(NewOrderAddressEvent.OnBackClick) }
                    }
                }
            }
        }
    }
}

@Composable
private fun DepartureAddressItemView(
    model: AddressUiModel,
    eventHandler: (NewOrderAddressEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Theme.shapes.textFields)
            .clickable {
                eventHandler(NewOrderAddressEvent.OnAddressClick(model))
            }
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            modifier = Modifier.size(20.dp),
            selected = model.isActive,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = Theme.colors.radioButtonColor,
                unselectedColor = Theme.colors.radioButtonColor
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .weight(1f),
        ) {
            AddressView(model)
        }
        IconButton(
            onClick = { eventHandler(NewOrderAddressEvent.OnEditClick(model)) }) {
            Icon(
                painter = painterResource(id = R_core.drawable.edit_ic),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun DepartureAddNewAddressView(eventHandler: (NewOrderAddressEvent) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(Theme.shapes.textFields)
        .clickable { eventHandler(NewOrderAddressEvent.OnAddAddressClick) }
        .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R_core.string.common_add_new_address)
        )
        Icon(
            modifier = Modifier.padding(start = 16.dp, end = 4.dp),
            painter = painterResource(id = R_core.drawable.add_ic),
            contentDescription = null
        )
    }
}