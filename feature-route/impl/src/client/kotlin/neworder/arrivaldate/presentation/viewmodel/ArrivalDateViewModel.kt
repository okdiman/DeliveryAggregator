package neworder.arrivaldate.presentation.viewmodel

import BaseViewModel
import neworder.arrivaldate.presentation.ArrivalDateParameters
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateAction
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateEvent
import neworder.arrivaldate.presentation.viewmodel.model.ArrivalDateState
import utils.CommonConstants.Helpers.SPACER
import utils.ext.DateFormats
import utils.ext.toLocalDate
import view.calendar.constants.CalendarConstants
import view.calendar.constants.CalendarConstants.DAYS_IN_WEEK
import view.calendar.constants.CalendarConstants.DEFAULT_DAYS_ARRAY
import view.calendar.model.CalendarDateUiModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ArrivalDateViewModel(
    private val parameters: ArrivalDateParameters
) : BaseViewModel<ArrivalDateState, ArrivalDateAction, ArrivalDateEvent>(
    initialState = ArrivalDateState()
) {
    private var calendar = Calendar.getInstance()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: ArrivalDateEvent) {
        when (viewEvent) {
            is ArrivalDateEvent.OnArrowClick -> onArrowClick(viewEvent.arrowId)
            is ArrivalDateEvent.OnDateClick -> onDateClick(viewEvent.date)
            ArrivalDateEvent.ResetAction -> onResetAction()
            ArrivalDateEvent.ResetCalendar -> onResetCalendar()
            ArrivalDateEvent.OnBackClick -> onBackClick()
        }
    }

    private fun getContent() {
        val blockedDates = parameters.unavailableDates.map { date ->
            date.toLocalDate(DateFormats.YEAR_MONTH_DAY)
        }.filter {
            it.monthValue == calendar.get(Calendar.MONTH) + 1
        }.map {
            it.dayOfMonth
        }
        val list = mutableListOf<CalendarDateUiModel>()
        val startDay = getStartDayOfWeek()
        val daysLastMonth = getMonthDaysCount(calendar.get(Calendar.MONTH).toPastMonth())
        val daysCurrentMonth = getMonthDaysCount(calendar.get(Calendar.MONTH))
        val startDayLastMonth = daysLastMonth - startDay + 1

        if (startDay != 1) {
            for (i in 1 until startDay) {
                list.add(CalendarDateUiModel(startDayLastMonth + i, isBlocked = true))
            }
        }
        val unavailableWeekDays = DEFAULT_DAYS_ARRAY - parameters.weekWorkDays.map { it.toInt() }.toSet()
        val auxiliaryCalendar = Calendar.getInstance()
        for (i in 1..daysCurrentMonth) {
            auxiliaryCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i)
            val currentDayOfWeek = auxiliaryCalendar.get(Calendar.DAY_OF_WEEK)
            val isBlocked = unavailableWeekDays.any { unavailableDay -> currentDayOfWeek - 1 == unavailableDay }
            list.add(
                CalendarDateUiModel(
                    day = i,
                    isWeekend = (i + startDay) % DAYS_IN_WEEK == 0 || (i + startDay - 1) % DAYS_IN_WEEK == 0,
                    isSelected = i == calendar.get(Calendar.DAY_OF_MONTH) + startDay - 2,
                    isBlocked = blockedDates.contains(i) || isBlocked
                )
            )
        }
        viewState = viewState.copy(
            dates = list,
            month = getMonth(),
            defaultIndex = calendar.get(Calendar.DAY_OF_MONTH) + startDay - 1
        )
    }

    private fun getMonth(): String {
        val formatter = SimpleDateFormat(DateFormats.MONTH_FORMAT, Locale.getDefault())
        val month = formatter.format(calendar.time).run {
            substring(0, 1).uppercase(Locale.getDefault()) + substring(1)
        }
        return buildString { append(month + SPACER + calendar.get(Calendar.YEAR)) }
    }

    private fun onResetCalendar() {
        calendar = Calendar.getInstance()
        getContent()
    }

    private fun onBackClick() {
        viewAction = ArrivalDateAction.OpenPreviousScreen
    }

    private fun onArrowClick(arrowId: Int) {
        when (arrowId) {
            CalendarConstants.ID_ARROW_NEXT -> setNextMonth()
            CalendarConstants.ID_ARROW_PAST -> setPastMonth()
            else -> throw IndexOutOfBoundsException("invalid index")
        }
    }

    private fun onDateClick(date: CalendarDateUiModel) {
        if (isAvailableDate(date.day) && !date.isBlocked) {
            viewState = viewState.copy(
                dates = viewState.dates.map {
                    it.isSelected = it.day == date.day
                    it
                }
            )
            Calendar.getInstance().run {
                set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), date.day)
                viewAction = ArrivalDateAction.UpdateNewOrderScreen(time)
            }
        }
    }

    private fun isAvailableDate(day: Int): Boolean {
        val newCalendar = Calendar.getInstance().apply {
            set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), day)
        }
        return Calendar.getInstance().before(newCalendar)
    }

    private fun getStartDayOfWeek() = Calendar.getInstance().run {
        set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1)
        val startDay = get(Calendar.DAY_OF_WEEK)
        if (startDay == 1) DAYS_IN_WEEK else startDay - 1
    }

    private fun getMonthDaysCount(month: Int) = Calendar.getInstance().run {
        set(Calendar.MONTH, month)
        getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun setNextMonth() {
        calendar.apply {
            if (get(Calendar.MONTH) != Calendar.DECEMBER) {
                set(Calendar.MONTH, get(Calendar.MONTH) + 1)
            } else {
                set(Calendar.MONTH, Calendar.JANUARY)
                set(Calendar.YEAR, get(Calendar.YEAR) + 1)
            }
        }
        getContent()
    }

    private fun setPastMonth() {
        calendar.apply {
            if (get(Calendar.MONTH) != Calendar.JANUARY) {
                set(Calendar.MONTH, get(Calendar.MONTH) - 1)
            } else {
                set(Calendar.YEAR, get(Calendar.YEAR) - 1)
                set(Calendar.MONTH, Calendar.DECEMBER)
            }
        }
        getContent()
    }

    private fun Int.toPastMonth() = if (this - 1 == 0) Calendar.DECEMBER else this - 1
}