package view.calendar.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.Theme
import view.calendar.constants.CalendarConstants.DAYS_IN_WEEK
import view.calendar.model.CalendarDateUiModel
import view.calendar.model.CalendarDaysOfWeek
import view.calendar.model.CalendarState

@Suppress("LongParameterList")
@Composable
internal fun CalendarDataView(
    dateState: CalendarState,
    weekTextStyle: TextStyle,
    dateTextStyle: TextStyle,
    selectorColor: Color,
    inactiveDateColor: Color,
    activeDateColor: Color,
    weekendDateColor: Color,
    onClick: (date: CalendarDateUiModel) -> Unit,
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        userScrollEnabled = false,
        columns = GridCells.Fixed(DAYS_IN_WEEK)
    ) {
        items(CalendarDaysOfWeek.values()) { dayOfWeek ->
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = dayOfWeek.text,
                textAlign = TextAlign.Center,
                style = weekTextStyle
            )
        }
        items(dateState.dates) { date ->
            Box(
                modifier = Modifier
                    .clip(shape = Theme.shapes.roundedButton)
                    .background(
                        color = if (date.isSelected) selectorColor else Color.Transparent,
                        shape = Theme.shapes.roundedButton
                    )
                    .clickable {
                        onClick(date)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = when {
                        date.isPastMonth -> inactiveDateColor
                        date.isWeekend -> weekendDateColor
                        else -> activeDateColor
                    },
                    text = date.day.toString(),
                    textAlign = TextAlign.Center,
                    style = dateTextStyle,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clip(shape = Theme.shapes.roundedButton)
                )
            }
        }
    }
}