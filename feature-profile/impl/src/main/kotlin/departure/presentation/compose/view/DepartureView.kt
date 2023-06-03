package departure.presentation.compose.view

import CommonErrorScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.sp
import departure.presentation.viewmodel.model.DepartureEvent
import departure.presentation.viewmodel.model.DepartureState
import presentation.AddressView
import presentation.model.AddressUiModel
import root.presentation.AddressLoadingView
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_profile.impl.R
import view.BackButtonView
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun DepartureView(state: DepartureState, eventHandler: (DepartureEvent) -> Unit) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(DepartureEvent.OnRetryClick) }
    } else {
        val startState = remember { MutableTransitionState(false) }.also {
            it.targetState = true
        }
        AnimatedVisibility(visibleState = startState, enter = slideInHorizontally()) {
            LazyColumn(
                modifier = Modifier
                    .padding(PaddingValues(start = 8.dp, end = 8.dp))
            ) {
                item {
                    DepartureTitleView(eventHandler)
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
                }
            }
        }
    }
}

@Composable
private fun DepartureTitleView(eventHandler: (DepartureEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 8.dp, end = 8.dp)
    ) {
        BackButtonView { eventHandler(DepartureEvent.OnBackClick) }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.profile_depart_address),
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
    }
    Spacer(Modifier.height(12.dp))
}

@Composable
private fun DepartureAddressItemView(
    model: AddressUiModel,
    eventHandler: (DepartureEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Theme.shapes.textFields)
            .clickable {
                eventHandler(DepartureEvent.OnAddressClick(model.id))
            }
            .padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
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
        IconButton(onClick = { eventHandler(DepartureEvent.OnEditClick(model)) }) {
            Icon(
                painter = painterResource(id = R_core.drawable.edit_ic),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun DepartureAddNewAddressView(eventHandler: (DepartureEvent) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(Theme.shapes.textFields)
        .clickable { eventHandler(DepartureEvent.OnAddAddressClick) }
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