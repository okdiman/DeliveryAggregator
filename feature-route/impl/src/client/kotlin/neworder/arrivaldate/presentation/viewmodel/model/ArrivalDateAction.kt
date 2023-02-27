package neworder.arrivaldate.presentation.viewmodel.model

import java.util.Date

interface ArrivalDateAction {
    data class UpdateNewOrderScreen(val date: Date) : ArrivalDateAction
    object OpenPreviousScreen : ArrivalDateAction
}