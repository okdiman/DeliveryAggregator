package view.calendar.model

open class CalendarState(
    open val dates: List<CalendarDateUiModel>,
    open val month: String,
    open val defaultIndex: Int
)