package orderdetails.extras.presentation

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
import orderdetails.loadingstate.presentation.compose.model.OrderLoadingExtrasUiModel
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingState
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core
import view.BSTitleView
import view.CheckboxView

@Composable
internal fun ExtrasScreen(
    state: OrderLoadingState,
    onExtrasClick: (List<OrderLoadingExtrasUiModel>) -> Unit
) {
    val rootController = LocalRootController.current
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        item {
            BSTitleView(stringResource(id = R.string.loading_add_info))
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
    state: OrderLoadingState,
    onExtrasClick: (List<OrderLoadingExtrasUiModel>) -> Unit
) {
    state.extras.uiModel.forEach { uiModel ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Theme.shapes.textFields)
                .selectable(
                    selected = state.extras.stateText.contains(uiModel.text),
                    onClick = {
                        val result = getExtras(uiModel, state.extras.extrasActive)
                        onExtrasClick(result)
                    },
                    role = Role.RadioButton
                )
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CheckboxView(
                checked = state.extras.extrasActive.contains(uiModel),
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

private fun getExtras(selectedExtra: OrderLoadingExtrasUiModel, activeExtrasList: List<OrderLoadingExtrasUiModel>) =
    when {
        selectedExtra == OrderLoadingExtrasUiModel.Default && !activeExtrasList.contains(selectedExtra) -> {
            listOf(OrderLoadingExtrasUiModel.Default)
        }
        activeExtrasList.contains(selectedExtra) -> activeExtrasList - selectedExtra
        else -> activeExtrasList + selectedExtra - OrderLoadingExtrasUiModel.Default
    }