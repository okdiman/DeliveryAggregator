package extras.presentation

import ActionButton
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import extras.presentation.model.ExtrasState
import extras.presentation.model.ExtrasUiModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import view.BSTitleView
import view.CheckboxView
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun ExtrasScreen(
    state: ExtrasState,
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
            ExtrasCheckboxesView(state, onExtrasClick)
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
                modifier = Modifier.padding(horizontal = 6.dp),
                text = uiModel.text,
                style = Theme.fonts.regular
            )
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