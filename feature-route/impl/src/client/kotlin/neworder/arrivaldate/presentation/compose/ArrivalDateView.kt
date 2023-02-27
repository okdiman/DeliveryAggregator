package neworder.arrivaldate.presentation.compose

import ActionButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateEvent
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateState
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BackButton
import view.calendar.CalendarView

@Composable
internal fun ArrivalDateView(state: ArrivalDateState, eventHandler: (ArrivalDateEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        ArrivalDateTitle(eventHandler)
        CalendarView(
            modifier = Modifier.padding(top = 16.dp),
            state = state,
            onMonthClick = { eventHandler(ArrivalDateEvent.OnArrowClick(it)) }
        ) { eventHandler(ArrivalDateEvent.OnDateClick(it)) }
        ActionButton(
            textRes = R.string.new_order_date_button_text,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 24.dp)
        ) { eventHandler(ArrivalDateEvent.OnBackClick) }
    }
}

@Composable
private fun ArrivalDateTitle(eventHandler: (ArrivalDateEvent) -> Unit) {
    val rootController = LocalRootController.current
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 16.dp, start = 5.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton(modifier = Modifier.padding(top = 3.dp)) {
            rootController.findModalController().popBackStack(null)
        }
        Text(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f),
            text = stringResource(R.string.new_order_date_title),
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                eventHandler(ArrivalDateEvent.ResetCalendar)
            },
            text = stringResource(R.string.new_order_date_reset),
            style = Theme.fonts.regular
        )
    }
}