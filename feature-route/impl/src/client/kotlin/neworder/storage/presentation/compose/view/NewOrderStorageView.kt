package neworder.storage.presentation.compose.view

import CommonErrorScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import neworder.storage.presentation.viewmodel.model.NewOrderStorageEvent
import neworder.storage.presentation.viewmodel.model.NewOrderStorageState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BackButtonView

@Composable
internal fun NewOrderStorageView(
    state: NewOrderStorageState,
    eventHandler: (NewOrderStorageEvent) -> Unit
) {
    if (state.isError) {
        CommonErrorScreen {
            eventHandler(NewOrderStorageEvent.OnRetryClick)
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            item {
                NewOrderStorageTitle(eventHandler)
            }
            if (state.isLoading) {
                item {
                    NewOrderStorageLoadingView()
                }
            } else {
                item {
                    StorageRadioButtonGroupView(state, eventHandler)
                }
            }
        }
    }
}

@Composable
private fun NewOrderStorageTitle(eventHandler: (NewOrderStorageEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 16.dp, start = 5.dp, end = 8.dp)
    ) {
        BackButtonView { eventHandler(NewOrderStorageEvent.OnBackClick) }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.common_delivery_adddress),
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
    }
}

@Composable
private fun StorageRadioButtonGroupView(
    state: NewOrderStorageState,
    eventHandler: (NewOrderStorageEvent) -> Unit
) {
    state.storages.forEach { storage ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Theme.shapes.textFields)
                .selectable(
                    selected = storage.name == state.activeStorage,
                    onClick = {
                        eventHandler(NewOrderStorageEvent.OnStorageClick(
                            state.storages.first { it.name == storage.name }
                        ))
                    },
                    role = Role.RadioButton
                )
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            RadioButton(
                modifier = Modifier.size(20.dp),
                selected = storage.name == state.activeStorage,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Theme.colors.radioButtonColor,
                    unselectedColor = Theme.colors.radioButtonColor
                )
            )
            Text(
                modifier = Modifier.padding(horizontal = 6.dp),
                text = storage.name,
                style = Theme.fonts.regular
            )
        }
    }
}