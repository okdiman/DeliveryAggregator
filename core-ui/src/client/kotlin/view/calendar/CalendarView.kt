package view.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import theme.Theme
import view.calendar.model.CalendarDateUiModel
import view.calendar.model.CalendarState
import view.calendar.view.CalendarDataView
import view.calendar.view.CalendarMonthView

@Suppress("LongParameterList")
@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    state: CalendarState,
    monthTextStyle: TextStyle = Theme.fonts.bold,
    weekTextStyle: TextStyle = Theme.fonts.regular,
    dateTextStyle: TextStyle = Theme.fonts.regular,
    selectorColor: Color = Theme.colors.calendarBackgroundColor,
    inactiveDateColor: Color = Theme.colors.disabledTextColor,
    activeDateColor: Color = Theme.colors.textPrimaryColor,
    weekendDateColor: Color = Theme.colors.selectionTextColor,
    onMonthClick: (id: Int) -> Unit,
    onClickDate: (date: CalendarDateUiModel) -> Unit,
) {
    Column(modifier = modifier) {
        CalendarMonthView(
            month = state.month,
            textStyle = monthTextStyle
        ) { onMonthClick(it) }
        CalendarDataView(
            dateState = state,
            weekTextStyle = weekTextStyle,
            dateTextStyle = dateTextStyle,
            selectorColor = selectorColor,
            inactiveDateColor = inactiveDateColor,
            activeDateColor = activeDateColor,
            weekendDateColor = weekendDateColor,
        ) { onClickDate(it) }
    }
}