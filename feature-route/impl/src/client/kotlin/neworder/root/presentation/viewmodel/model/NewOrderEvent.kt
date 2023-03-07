package neworder.root.presentation.viewmodel.model

import cargotype.domain.model.CargoType
import extras.presentation.model.ExtrasUiModel
import neworder.arrivaltime.domain.ArrivalTime
import presentation.model.AddressUiModel
import root.presentation.compose.model.RouteStorageModel
import java.util.Date

sealed interface NewOrderEvent {
    data class OnBoxesCountChanged(val count: String) : NewOrderEvent
    data class OnPalletsCountChanged(val count: String) : NewOrderEvent
    data class OnCargoTypeChanged(val type: CargoType) : NewOrderEvent
    data class OnExtrasChanged(val extras: List<ExtrasUiModel>) : NewOrderEvent
    data class OnWeightChanged(val weight: String) : NewOrderEvent
    data class OnBSAddressChanged(val bsAddress: AddressUiModel) : NewOrderEvent
    data class OnArrivalDateChanged(val date: Date) : NewOrderEvent
    data class OnArrivalTimeChanged(val time: ArrivalTime) : NewOrderEvent
    data class OnStorageChanged(val storage: RouteStorageModel) : NewOrderEvent
    data class OnCommentChanged(val comment: String) : NewOrderEvent
    object OnCargoTypeClick : NewOrderEvent
    object OnAddressClick : NewOrderEvent
    object OnArrivalDateClick : NewOrderEvent
    object OnArrivalTimeClick : NewOrderEvent
    object OnStorageClick : NewOrderEvent
    object OnExtrasClick : NewOrderEvent
    object OnBackClick : NewOrderEvent
    object OnCreateButtonClick : NewOrderEvent
    object OnRetryClick : NewOrderEvent
    object ResetAction : NewOrderEvent
}