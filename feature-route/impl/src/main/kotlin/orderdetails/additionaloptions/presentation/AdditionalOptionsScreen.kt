package orderdetails.additionaloptions.presentation

import ActionButton
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import orderdetails.additionaloptions.presentation.domain.AdditionalOptionsModel
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingState
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BSTitleView

@Composable
internal fun AdditionalOptionsScreen(
    state: OrderLoadingState,
    onAdditionalOptionsClick: (List<String>) -> Unit
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
            AdditionalOptionsCheckboxesView(state, onAdditionalOptionsClick)
        }
        item {
            ActionButton(
                textRes = R.string.loading_step_complete,
                alignment = Alignment.Center,
                padding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
            ) { rootController.findModalController().popBackStack(null) }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AdditionalOptionsCheckboxesView(
    state: OrderLoadingState,
    onAdditionalOptionsClick: (List<String>) -> Unit
) {
    val additionalOptions = AdditionalOptionsModel.values().map { it.text }
    additionalOptions.forEach { label ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Theme.shapes.textFields)
                .selectable(
                    selected = label == state.cargoType.stateText,
                    onClick = {
                        val result = getAdditionalOptions(label, state.additionalOptions.optionsList)
                        onAdditionalOptionsClick(result)
                    },
                    role = Role.RadioButton
                )
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /**
            Без провайдера используются отступы, которые нельзя контролировать
             */
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                Checkbox(
                    checked = state.additionalOptions.optionsList.contains(label),
                    colors = CheckboxDefaults.colors(
                        checkedColor = Theme.colors.radioButtonColor,
                        uncheckedColor = Theme.colors.radioButtonColor
                    ),
                    onCheckedChange = null
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 6.dp),
                text = label,
                style = Theme.fonts.regular
            )
        }
    }
}

private fun getAdditionalOptions(label: String, activeOptionsList: List<String>) = when {
    label == AdditionalOptionsModel.NotNeed.text && !activeOptionsList.contains(label) -> {
        listOf(label)
    }
    activeOptionsList.contains(label) -> activeOptionsList - label
    else -> activeOptionsList + label - AdditionalOptionsModel.NotNeed.text
}