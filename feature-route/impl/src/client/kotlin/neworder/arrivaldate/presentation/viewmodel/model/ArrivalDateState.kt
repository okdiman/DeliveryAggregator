package neworder.arrivaldate.presentation.viewmodel.model

import view.calendar.model.CalendarDateUiModel
import view.calendar.model.CalendarState

data class ArrivalDateState(
    override val dates: List<CalendarDateUiModel> = emptyList(),
    override val month: String = "",
    override val defaultIndex: Int = 0
) : CalendarState(dates, month, defaultIndex)