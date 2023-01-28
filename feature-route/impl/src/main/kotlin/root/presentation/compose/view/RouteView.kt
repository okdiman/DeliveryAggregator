package root.presentation.compose.view

import CommonErrorScreen
import ScrollScreenActionButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import root.domain.model.RouteStatusProgress
import root.presentation.viewmodel.model.RouteEvent
import root.presentation.viewmodel.model.RouteState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Suppress("LongMethod")
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RouteView(state: RouteState, eventHandler: (RouteEvent) -> Unit) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(RouteEvent.OnRetryClick) }
    } else {
        val startState = remember { MutableTransitionState(false) }.also {
            it.targetState = true
        }
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
            AnimatedVisibility(
                visibleState = startState,
                enter = slideInHorizontally()
            ) {
                val isAcceptButtonAvailable =
                    state.status == RouteStatusProgress.NEW && !state.isLoading
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
                ) {
                    item {
                        RouteNotificationsView(state, eventHandler)
                    }
                    item {
                        RouteTitleView()
                    }
                    when {
                        state.isLoading -> {
                            item {
                                RouteLoadingView()
                            }
                        }
                        state.orders.isNotEmpty() -> {
                            item { Spacer(modifier = Modifier.height(16.dp)) }
                            itemsIndexed(state.orders) { index, item ->
                                RoutesOrderView(index, item, eventHandler)
                            }
                            item {
                                val height = if (isAcceptButtonAvailable) {
                                    100.dp
                                } else 16.dp
                                Spacer(modifier = Modifier.height(height))
                            }
                        }
                        else -> {
                            item {
                                PlaceholderView()
                            }
                        }
                    }
                }
                if (isAcceptButtonAvailable) {
                    RouteAcceptButtonView(state, eventHandler)
                }
            }
            RouteCheckNotificationPermission(state, eventHandler)
        }
    }
}

@Composable
private fun RouteNotificationsView(state: RouteState, eventHandler: (RouteEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
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

@Composable
private fun RouteTitleView() {
    Spacer(Modifier.height(14.dp))
    Text(
        text = stringResource(R_core.string.navigation_route),
        style = Theme.fonts.bold.copy(fontSize = 24.sp)
    )
}

@Composable
private fun RouteAcceptButtonView(state: RouteState, eventHandler: (RouteEvent) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            textRes = R.string.route_accept_route,
            additionalText = state.buttonUiModel.text,
            height = 65.dp,
            padding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) { eventHandler(RouteEvent.AcceptOrderClick) }
    }
}