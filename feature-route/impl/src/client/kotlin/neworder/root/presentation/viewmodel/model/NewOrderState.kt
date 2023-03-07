package neworder.root.presentation.viewmodel.model

import extras.presentation.model.ExtrasState
import neworder.root.presentation.compose.model.NewOrderParamState
import root.presentation.model.AddressState

data class NewOrderState(
    val marketplace: NewOrderParamState.MarketplaceState = NewOrderParamState.MarketplaceState.DEFAULT,
    val cargoType: NewOrderParamState.CargoTypeState = NewOrderParamState.CargoTypeState(),
    val boxesCount: NewOrderParamState.BoxesCountState = NewOrderParamState.BoxesCountState(),
    val palletsCount: NewOrderParamState.PalletsCountState = NewOrderParamState.PalletsCountState(),
    val weight: NewOrderParamState.WeightState = NewOrderParamState.WeightState(),
    val address: AddressState = AddressState(),
    val arrivalDate: NewOrderParamState.ArrivalDateState = NewOrderParamState.ArrivalDateState(),
    val arrivalTime: NewOrderParamState.ArrivalTimeState = NewOrderParamState.ArrivalTimeState(),
    val storage: NewOrderParamState.StorageState = NewOrderParamState.StorageState(),
    val extras: ExtrasState = ExtrasState(),
    val comment: NewOrderParamState.CommentState = NewOrderParamState.CommentState(),
    val createButton: NewOrderParamState.CreateButtonState = NewOrderParamState.CreateButtonState(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)