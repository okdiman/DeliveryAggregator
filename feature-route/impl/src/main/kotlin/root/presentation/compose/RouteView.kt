package root.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import root.presentation.viewmodel.model.RouteEvent
import root.presentation.viewmodel.model.RouteState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_route.impl.R
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
fun RouteView(state: RouteState, eventHandler: (RouteEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
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
        Spacer(Modifier.height(14.dp))
        Text(
            text = stringResource(R_core.string.route),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        if (state.routes.isNotEmpty()) {
            RoutesListView(state, eventHandler)
        } else {
            PlaceholderView()
        }
    }
}

@Composable
private fun RoutesListView(state: RouteState, eventHandler: (RouteEvent) -> Unit) {

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