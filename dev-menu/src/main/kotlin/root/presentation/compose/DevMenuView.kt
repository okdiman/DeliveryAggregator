package root.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import root.presentation.viewmodel.model.DevMenuEvent
import root.presentation.viewmodel.model.DevMenuState
import theme.Theme
import trinity_monsters.delivery_aggregator.dev_menu.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Suppress("LongMethod")
@Composable
internal fun DevMenuView(state: DevMenuState, eventHandler: (DevMenuEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Theme.colors.selectionTextColor)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    style = Theme.fonts.bold.copy(fontSize = 22.sp, color = Theme.colors.textSecondaryColor),
                    text = stringResource(id = R_core.string.common_dev_menu)
                )
            }
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = Theme.fonts.bold.copy(fontSize = 20.sp),
                text = stringResource(id = R.string.dev_menu_hosts_title)
            )
        }
        items(state.hosts) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Theme.shapes.textFields)
                    .clickable {
                        eventHandler(DevMenuEvent.OnHostClick(it))
                    }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.size(20.dp),
                    selected = it.isActive,
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Theme.colors.radioButtonColor,
                        unselectedColor = Theme.colors.radioButtonColor
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    style = Theme.fonts.regular,
                    text = it.name
                )
            }
        }
        item {
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = Theme.fonts.bold,
                    text = stringResource(id = R.string.dev_menu_firebase_token)
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            eventHandler(DevMenuEvent.OnTokenClick)
                        },
                    style = Theme.fonts.regular.copy(fontSize = 12.sp),
                    text = state.pushToken
                )
            }
        }
    }
}