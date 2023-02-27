package neworder.arrivaltime.presentation

import ActionButton
import androidx.compose.foundation.layout.PaddingValues
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
import neworder.arrivaltime.domain.ArrivalTime
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BSTitleView
import view.model.DefaultParamState

@Composable
internal fun ArrivalTimeScreen(
    state: DefaultParamState,
    onArrivalTimeClick: (ArrivalTime) -> Unit
) {
    val rootController = LocalRootController.current
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        item {
            BSTitleView(stringResource(id = R.string.route_delivery_time))
        }
        item {
            ArrivalTimeRadioButtonGroupView(state, onArrivalTimeClick)
        }
        item {
            ActionButton(
                textRes = trinity_monsters.delivery_aggregator.core_ui.R.string.common_complete,
                alignment = Alignment.Center,
                padding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
            ) { rootController.findModalController().popBackStack(null) }
        }
    }
}

@Composable
private fun ArrivalTimeRadioButtonGroupView(
    state: DefaultParamState,
    onArrivalTimeClick: (ArrivalTime) -> Unit
) {
    val radioOptions = ArrivalTime.values().map { it.range }
    radioOptions.forEach { label ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Theme.shapes.textFields)
                .selectable(
                    selected = label == state.stateText,
                    onClick = {
                        onArrivalTimeClick(
                            ArrivalTime
                                .values()
                                .first { it.range == label }
                        )
                    },
                    role = Role.RadioButton
                )
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            RadioButton(
                modifier = Modifier.size(20.dp),
                selected = label == state.stateText,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Theme.colors.radioButtonColor,
                    unselectedColor = Theme.colors.radioButtonColor
                )
            )
            Text(
                modifier = Modifier.padding(horizontal = 6.dp),
                text = label,
                style = Theme.fonts.regular
            )
        }
    }
}