package orderdetails.loadingstate.presentation.viewmodel

import BaseViewModel
import orderdetails.cargotype.domain.model.OrderLoadingCargoType
import orderdetails.loadingstate.presentation.OrderStateParameters
import orderdetails.loadingstate.presentation.compose.model.OrderLoadingParamState
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingAction
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingEvent
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingState
import utils.CommonConstants.Helpers.COMMA

class OrderLoadingViewModel(private val parameters: OrderStateParameters) :
    BaseViewModel<OrderLoadingState, OrderLoadingAction, OrderLoadingEvent>(
        initialState = OrderLoadingState()
    ) {

    override fun obtainEvent(viewEvent: OrderLoadingEvent) {
        when (viewEvent) {
            is OrderLoadingEvent.OnAdditionalOptionsChanged -> onAdditionalOptionsChanged(viewEvent.options)
            is OrderLoadingEvent.OnBoxesCountChanged -> onBoxesCountChanged(viewEvent.count)
            is OrderLoadingEvent.OnCargoTypeChanged -> onCargoTypeChanged(viewEvent.type)
            is OrderLoadingEvent.OnPalletsCountChanged -> onPalletsCountChanged(viewEvent.count)
            OrderLoadingEvent.OnBackClick -> onBackClick()
            OrderLoadingEvent.OnDoneButtonClick -> onDoneButtonClick()
            OrderLoadingEvent.OnPhotoClick -> onPhotoClick()
            OrderLoadingEvent.ResetAction -> onResetAction()
            OrderLoadingEvent.OnOpenAdditionalOptBSClick -> onOpenAdditionalOptBSClick()
            OrderLoadingEvent.OnOpenCargoTypeBSClick -> onOpenCargoTypeBSClick()
        }
    }

    private fun onAdditionalOptionsChanged(options: List<String>) {
        viewState = viewState.copy(
            additionalOptions = OrderLoadingParamState.AdditionalOptionsState(
                stateText = options.joinToString(COMMA),
                optionsList = options
            )
        )
    }

    private fun onBoxesCountChanged(count: String) {
        viewState = viewState.copy(
            boxesCount = OrderLoadingParamState.BoxesCountState(
                stateText = count
            )
        )
    }

    private fun onCargoTypeChanged(cargoType: OrderLoadingCargoType) {
        viewState = viewState.copy(
            cargoType = OrderLoadingParamState.CargoTypeState(
                stateText = cargoType.text,
                cargoType = cargoType
            )
        )
    }

    private fun onPalletsCountChanged(count: String) {
        viewState = viewState.copy(
            palletsCount = OrderLoadingParamState.PalletsCountState(
                stateText = count
            )
        )
    }

    private fun onBackClick() {
        viewAction = OrderLoadingAction.OpenPreviousScreen
    }

    private fun onDoneButtonClick() {
        //TODO запрос на смену статуса
        viewAction = OrderLoadingAction.OpenPreviousScreen
    }

    private fun onPhotoClick() {
        viewAction = OrderLoadingAction.OpenCamera
    }

    private fun onOpenCargoTypeBSClick() {
        viewAction = OrderLoadingAction.OpenCargoTypeScreen
    }

    private fun onOpenAdditionalOptBSClick() {
        viewAction = OrderLoadingAction.OpenAdditionalOptionsScreen
    }
}