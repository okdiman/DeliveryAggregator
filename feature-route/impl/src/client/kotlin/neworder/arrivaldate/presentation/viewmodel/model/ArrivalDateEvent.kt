package neworder.arrivaldate.presentation.viewmodel.model

import view.calendar.model.CalendarDateUiModel

sealed interface ArrivalDateEvent {
    data class OnArrowClick(val arrowId: Int) : ArrivalDateEvent
    data class OnDateClick(val date: CalendarDateUiModel) : ArrivalDateEvent
    object ResetCalendar : ArrivalDateEvent
    object OnBackClick : ArrivalDateEvent
    object ResetAction : ArrivalDateEvent
}