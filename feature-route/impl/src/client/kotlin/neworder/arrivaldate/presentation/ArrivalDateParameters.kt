package neworder.arrivaldate.presentation

import androidx.compose.runtime.Immutable

@Immutable
class ArrivalDateParameters(
    val unavailableDates: List<String>,
    val weekWorkDays: List<String>
)