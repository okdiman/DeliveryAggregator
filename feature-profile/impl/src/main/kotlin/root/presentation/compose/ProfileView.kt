package root.presentation.compose

import ErrorScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import root.presentation.compose.model.ProfileItemUiModel
import root.presentation.viewmodel.model.ProfileEvent
import root.presentation.viewmodel.model.ProfileState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl.R
import view.ProgressIndicator
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
internal fun ProfileView(state: ProfileState, eventHandler: (ProfileEvent) -> Unit) {
    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ProgressIndicator()
            }
        }
        state.isError -> {
            ErrorScreen { eventHandler(ProfileEvent.OnRetryClick) }
        }
        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 54.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                item {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = state.name,
                                style = Theme.fonts.bold.copy(fontSize = 24.sp)
                            )
                        }
                        Icon(
                            modifier = Modifier
                                .clip(Theme.shapes.roundedButton)
                                .clickable {
                                    eventHandler(ProfileEvent.OnEditProfileClick)
                                },
                            painter = painterResource(id = R.drawable.profile_edit_ic),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = state.organizationName,
                        style = Theme.fonts.regular
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.phone, style = Theme.fonts.regular
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.email, style = Theme.fonts.regular
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(thickness = 1.dp, color = Theme.colors.hintColor)
                }
                items(state.uiModels) { ProfileItem(it, eventHandler) }
            }
        }
    }
}

@Composable
private fun ProfileItem(
    item: ProfileItemUiModel,
    eventHandler: (ProfileEvent) -> Unit
) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(Theme.shapes.textFields)
        .clickable {
            eventHandler(ProfileEvent.OnListItemClick(item))
        }) {
        Icon(painter = painterResource(id = item.icon), contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = item.title),
            style = Theme.fonts.bold
        )
        Icon(
            painter = painterResource(id = R_core.drawable.chevron_ic),
            contentDescription = null
        )
    }
}