package extras.presentation

import ActionButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import extras.presentation.model.ExtrasState
import extras.presentation.model.ExtrasUiModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BSTitleView
import view.CheckboxView
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun ExtrasScreen(
    state: ExtrasState,
    onExtraCountChanged: (ExtrasUiModel) -> Unit,
    onExtrasClick: (List<ExtrasUiModel>) -> Unit
) {
    val rootController = LocalRootController.current
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        item {
            BSTitleView(stringResource(id = R_core.string.common_extras))
        }
        item {
            ExtrasCheckboxesView(state, onExtraCountChanged, onExtrasClick)
        }
        item {
            ActionButton(
                textRes = R_core.string.common_complete,
                alignment = Alignment.Center,
                padding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
            ) { rootController.findModalController().popBackStack(null) }
        }
    }
}

@Composable
private fun ExtrasCheckboxesView(
    state: ExtrasState,
    onExtraCountChanged: (ExtrasUiModel) -> Unit,
    onExtrasClick: (List<ExtrasUiModel>) -> Unit
) {
    state.uiModel.forEach { uiModel ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Theme.shapes.textFields)
                .selectable(
                    selected = state.stateText.contains(uiModel.text),
                    onClick = {
                        val result = getExtras(uiModel, state.extrasActive)
                        onExtrasClick(result)
                    },
                    role = Role.RadioButton
                )
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CheckboxView(
                checked = state.extrasActive.contains(uiModel),
                colors = CheckboxDefaults.colors(
                    checkedColor = Theme.colors.radioButtonColor,
                    uncheckedColor = Theme.colors.radioButtonColor
                )
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .weight(1f),
                text = uiModel.text,
                style = Theme.fonts.regular
            )
            if (uiModel != ExtrasUiModel.Default) {
                ExtraCountersView(uiModel) { newCount ->
                    onExtraCountChanged(uiModel.copy(count = newCount))
                }
            }
        }
    }
}

@Composable
private fun ExtraCountersView(uiModel: ExtrasUiModel, onItemClick: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            modifier = Modifier.size(32.dp),
            onClick = { onItemClick(uiModel.count - 1) }
        ) {
            Image(painter = painterResource(id = R.drawable.extras_minus_ic), contentDescription = null)
        }
        Text(
            modifier = Modifier.padding(horizontal = 6.dp),
            text = uiModel.count.toString(),
            style = Theme.fonts.semiBold
        )
        IconButton(
            modifier = Modifier.size(32.dp),
            onClick = { onItemClick(uiModel.count + 1) }
        ) {
            Image(painter = painterResource(id = R.drawable.extras_plus_button), contentDescription = null)
        }
    }
}

private fun getExtras(selectedExtra: ExtrasUiModel, activeExtrasList: List<ExtrasUiModel>) =
    when {
        selectedExtra == ExtrasUiModel.Default && !activeExtrasList.contains(selectedExtra) -> {
            listOf(ExtrasUiModel.Default)
        }

        activeExtrasList.contains(selectedExtra) -> activeExtrasList - selectedExtra
        else -> activeExtrasList + selectedExtra - ExtrasUiModel.Default
    }