package root.presentation.compose.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
internal fun NotificationIconView(modifier: Modifier = Modifier, notificationsCount: Int, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        BadgedBox(
            badge = {
                if (notificationsCount > 0) {
                    Badge(backgroundColor = Theme.colors.badgeBackgroundColor) {
                        Text(
                            text = notificationsCount.toString(),
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
                    .clickable(onClick = onClick),
                painter = painterResource(R.drawable.route_notifications_ic),
                contentDescription = null
            )
        }
    }
}
