package root.presentation.compose.view

import CommonErrorScreen
import ScrollScreenActionButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import root.presentation.compose.model.OrderStatusCategoryUiModel
import root.presentation.compose.model.OrderUiModel
import root.presentation.viewmodel.model.OrdersEvent
import root.presentation.viewmodel.model.OrdersState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Suppress("LongMethod")
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun OrderRequestsView(state: OrdersState, eventHandler: (OrdersEvent) -> Unit) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(OrdersEvent.OnRetryClick) }
    } else {
        val startState = remember { MutableTransitionState(false) }.also {
            it.targetState = true
        }
        val pullRefreshState = rememberPullRefreshState(
            refreshing = state.isRefreshing,
            onRefresh = { eventHandler(OrdersEvent.OnRefreshSwipe) }
        )
        val categoryFilterScrollState = rememberScrollState()

        val noActiveOrdersFoundByFilter = state.selectedCategoryFilter == OrderStatusCategoryUiModel.ACTIVE &&
            state.filteredOrders.isEmpty()
        val showCreateNewOrder = state.orders.isEmpty() || noActiveOrdersFoundByFilter

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            AnimatedVisibility(
                visibleState = startState,
                enter = slideInHorizontally(),
            ) {
                Column {
                    NotificationIconView(
                        notificationsCount = state.notificationsCount
                    ) {
                        eventHandler(OrdersEvent.OnNotificationsClick)
                    }
                    OrderRequestsTitleView(modifier = Modifier.padding(horizontal = 16.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .horizontalScroll(categoryFilterScrollState)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        CategoriesFilterChips(state = state, eventHandler)
                    }
                    if (showCreateNewOrder && !state.isLoading) {
                        CreateNewOrderView(eventHandler)
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 16.dp),
                    ) {
                        when {
                            state.isLoading -> {
                                item {
                                    RouteLoadingView(modifier = Modifier.padding(horizontal = 16.dp))
                                }
                            }
                            state.selectedCategoryFilter != null && !showCreateNewOrder -> {
                                orderRequestItems(orderRequests = state.filteredOrders, eventHandler)
                            }
                            state.orders.isNotEmpty() -> {
                                orderRequestItems(orderRequests = state.orders, eventHandler)
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = state.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                scale = true
            )
        }
    }
}

@Composable
private fun OrderRequestsTitleView(modifier: Modifier = Modifier) {
    Spacer(Modifier.height(14.dp))
    Text(
        modifier = modifier,
        text = stringResource(R_core.string.navigation_order_requests),
        style = Theme.fonts.bold.copy(fontSize = 24.sp)
    )
}

private inline fun LazyListScope.orderRequestItems(
    orderRequests: List<OrderUiModel>,
    crossinline eventHandler: (OrdersEvent) -> Unit
) {
    itemsIndexed(orderRequests) { index, item ->
        OrderView(modifier = Modifier.padding(horizontal = 16.dp), index = index, model = item) { orderId, _ ->
            eventHandler(OrdersEvent.OnOpenOrderDetailsClick(orderId))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesFilterChips(state: OrdersState, eventHandler: (OrdersEvent) -> Unit) {
    OrderStatusCategoryUiModel.values().forEach { filter ->
        val isSelected = (state.selectedCategoryFilter == filter)
        val filteredCount = state.filteredOrders.size
        val filteredCountText = if (isSelected) " ($filteredCount)" else ""
        FilterChip(
            modifier = Modifier.animateContentSize(),
            enabled = state.orders.isNotEmpty(),
            selected = isSelected,
            colors = Theme.filterChipColors,
            onClick = { eventHandler(OrdersEvent.OnFilterByStatusClick(filter)) }
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .animateContentSize(),
                text = stringResource(filter.filterText) + filteredCountText,
                style = Theme.fonts.regular.copy(
                    fontSize = 20.sp,
                    color = if (isSelected) Color.White else Theme.colors.textFourthColor
                )
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Composable
private fun CreateNewOrderView(eventHandler: (OrdersEvent) -> Unit) {
    PlaceholderView(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxHeight(0.75f),
        placeHolderTitle = R.string.order_create_new,
        placeHolderSubtitle = R.string.order_created_orders_are_displayed_here
    )
    CreateNewOrderButtonView(eventHandler)
}

@Composable
private fun CreateNewOrderButtonView(eventHandler: (OrdersEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        ScrollScreenActionButton(
            alignment = Alignment.Center,
            text = stringResource(R.string.order_create),
            padding = PaddingValues(8.dp)
        ) { eventHandler(OrdersEvent.OnCreateNewOrderClick) }
    }
}