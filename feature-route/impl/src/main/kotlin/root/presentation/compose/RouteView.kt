package root.presentation.compose

import ErrorScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modifiers.advancedShadow
import root.presentation.compose.model.RouteOrderUiModel
import root.presentation.viewmodel.model.RouteEvent
import root.presentation.viewmodel.model.RouteState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.Helpers.NUMBER
import view.ProgressIndicator
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Suppress("LongMethod")
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RouteView(state: RouteState, eventHandler: (RouteEvent) -> Unit) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = { eventHandler(RouteEvent.OnRefreshSwipe) }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        PullRefreshIndicator(
            refreshing = state.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            scale = true
        )
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressIndicator()
                }
            }
            state.isError -> {
                ErrorScreen { eventHandler(RouteEvent.OnRetryClick) }
            }
            else -> {
                val startState = remember { MutableTransitionState(false) }.also {
                    it.targetState = true
                }
                AnimatedVisibility(
                    visibleState = startState,
                    enter = slideInHorizontally()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                BadgedBox(
                                    badge = {
                                        if (state.notificationsCount > 0) {
                                            Badge(backgroundColor = Theme.colors.badgeBackgroundColor) {
                                                Text(
                                                    text = state.notificationsCount.toString(),
                                                    style = Theme.fonts.regular.copy(
                                                        fontSize = 10.sp,
                                                        color = Theme.colors.textSecondaryColor
                                                    )
                                                )
                                            }
                                        }
                                    }) {
                                    Icon(
                                        modifier = Modifier
                                            .clip(Theme.shapes.roundedButton)
                                            .clickable {
                                                eventHandler(RouteEvent.OnNotificationsClick)
                                            },
                                        painter = painterResource(R.drawable.route_notifications_ic),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                        item {
                            Spacer(Modifier.height(14.dp))
                            Text(
                                text = stringResource(R_core.string.route),
                                style = Theme.fonts.bold.copy(fontSize = 24.sp)
                            )
                        }
                        if (state.orders.isNotEmpty()) {
                            item { Spacer(modifier = Modifier.height(16.dp)) }
                            itemsIndexed(state.orders) { index, item ->
                                RoutesOrdersView(index, item, eventHandler)
                            }
                        } else {
                            item {
                                PlaceholderView()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Suppress("LongMethod")
@Composable
private fun RoutesOrdersView(
    index: Int,
    model: RouteOrderUiModel,
    eventHandler: (RouteEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .advancedShadow(
                alpha = 0.1f,
                cornersRadius = 40.dp,
                shadowBlurRadius = 40.dp
            )
            .clip(Theme.shapes.roundedButton)
            .background(Color.White)
            .clickable { eventHandler(RouteEvent.OnRouteClick(model.id)) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = buildString {
                            append(NUMBER + model.id.toString())
                        },
                        style = Theme.fonts.bold.copy(fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = model.arrivalDate,
                        style = Theme.fonts.regular
                    )
                }
                if (model.status != null) {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clip(Theme.shapes.textFields)
                            .background(Theme.colors.hintBackgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                            text = stringResource(id = model.status.text),
                            style = Theme.fonts.regular
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(Theme.shapes.roundedButton)
                        .background(Theme.colors.departureImageBackgroundColor.copy(alpha = 0.08f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.route_departure_address_ic),
                        contentDescription = null
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.route_departure_address),
                        style = Theme.fonts.regular.copy(
                            color = Theme.colors.textPrimaryColor.copy(
                                alpha = 0.7f
                            )
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = model.departureAddress,
                        style = Theme.fonts.bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                modifier = Modifier.padding(start = 16.dp),
                painter = painterResource(id = R.drawable.route_connection_ic),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(Theme.shapes.roundedButton)
                        .background(Theme.colors.deliveryImageBackgroundColor.copy(alpha = 0.08f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.route_delivery_address_ic),
                        contentDescription = null
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.delivery_adddress),
                        style = Theme.fonts.regular.copy(
                            color = Theme.colors.textPrimaryColor.copy(
                                alpha = 0.7f
                            )
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = model.deliveryAddress,
                        style = Theme.fonts.bold
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(34.dp)
                        .clip(Theme.shapes.roundedButton)
                        .background(Theme.colors.hintBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = (index + 1).toString(), style = Theme.fonts.regular)
                }
            }
        }
    }
}

@Composable
private fun PlaceholderView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.route_placeholder_ic),
            contentDescription = null
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.routes_will_be_here),
            textAlign = TextAlign.Center,
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 36.dp),
            text = stringResource(R.string.route_can_includes_some_orders),
            textAlign = TextAlign.Center,
            style = Theme.fonts.regular
        )
    }
}