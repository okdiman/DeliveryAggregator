package neworder.arrivaldate.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateEvent
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateState
import ru.alexgladkov.odyssey.compose.helpers.noRippleClickable
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BackButtonView
import view.calendar.CalendarView

@Composable
internal fun ArrivalDateView(state: ArrivalDateState, eventHandler: (ArrivalDateEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        ArrivalDateTitle(eventHandler)
        CalendarView(
            state = state,
            onMonthClick = { eventHandler(ArrivalDateEvent.OnArrowClick(it)) }
        ) { eventHandler(ArrivalDateEvent.OnDateClick(it)) }
        ActionButton(
            textRes = R.string.new_order_date_button_text,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        ) { eventHandler(ArrivalDateEvent.OnBackClick) }
    }
}

@Composable
private fun ArrivalDateTitle(eventHandler: (ArrivalDateEvent) -> Unit) {
    val rootController = LocalRootController.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 24.dp, start = 10.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButtonView { rootController.findModalController().popBackStack(null) }
        Text(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f),
            text = stringResource(R.string.new_order_date_title),
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
        Text(
            modifier = Modifier.noRippleClickable {
                eventHandler(ArrivalDateEvent.ResetCalendar)
            },
            text = stringResource(R.string.new_order_date_reset),
            style = Theme.fonts.regular
        )
    }
}