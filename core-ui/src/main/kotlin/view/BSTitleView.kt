package view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.delivery_aggregator.core_ui.R

@Composable
fun BSTitleView(title: String) {
    val rootController = LocalRootController.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp, vertical = 16.dp),
            text = title,
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        Icon(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(Theme.shapes.roundedButton)
                .clickable {
                    rootController
                        .findModalController()
                        .popBackStack(null)
                },
            painter = painterResource(id = R.drawable.close_ic),
            contentDescription = null
        )
    }
}