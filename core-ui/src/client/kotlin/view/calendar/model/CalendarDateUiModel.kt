package view.calendar.model

class CalendarDateUiModel(
    val day: Int,
    var isSelected: Boolean = false,
    val isWeekend: Boolean = false,
    val isBlocked: Boolean = false,
)