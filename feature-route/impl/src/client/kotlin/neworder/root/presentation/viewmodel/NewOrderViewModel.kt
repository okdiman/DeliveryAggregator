package neworder.root.presentation.viewmodel

import BaseViewModel
import cargotype.domain.model.CargoType
import coroutines.AppDispatchers
import extras.domain.GetExtrasUseCase
import extras.presentation.mapper.ExtrasUiMapper
import extras.presentation.model.ExtrasState
import extras.presentation.model.ExtrasUiModel
import kotlinx.coroutines.Job
import network.domain.GetAuthTokenSyncUseCase
import neworder.arrivaltime.domain.ArrivalTime
import neworder.creationerror.presentation.CreationErrorParameters
import neworder.payment.domain.GetPaymentUriUseCase
import neworder.root.domain.NewOrderInteractor
import neworder.root.presentation.compose.model.NewOrderParamState
import neworder.root.presentation.mapper.NewOrderUiMapper
import neworder.root.presentation.viewmodel.model.NewOrderAction
import neworder.root.presentation.viewmodel.model.NewOrderEvent
import neworder.root.presentation.viewmodel.model.NewOrderState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.model.AddressUiModel
import root.domain.model.status.OrderStatusProgress
import root.domain.usecase.GetOrdersUseCase
import root.presentation.compose.model.RouteStorageModel
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants
import utils.CommonConstants.Helpers.RUBBLES
import utils.CommonConstants.Helpers.SPACER
import utils.ext.DateFormats.DAY_MONTH_YEAR_FORMAT
import utils.ext.DateFormats.FULL_DISPLAYED_DAY_MONTH
import utils.ext.toStringWithEnding
import utils.resource.domain.ResourceInteractor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewOrderViewModel : BaseViewModel<NewOrderState, NewOrderAction, NewOrderEvent>(
    initialState = NewOrderState()
), KoinComponent {

    private val getOrderRequests by inject<GetOrdersUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val getExtras by inject<GetExtrasUseCase>()
    private val extrasMapper by inject<ExtrasUiMapper>()
    private val interactor by inject<NewOrderInteractor>()
    private val modelMapper by inject<NewOrderUiMapper>()
    private val resourceInteractor by inject<ResourceInteractor>()
    private val getAuthTokenSyncUseCase by inject<GetAuthTokenSyncUseCase>()
    private val getPaymentUri by inject<GetPaymentUriUseCase>()

    private var priceJob: Job? = null

    init {
        getContent()
    }

    @Suppress("CyclomaticComplexMethod")
    override fun obtainEvent(viewEvent: NewOrderEvent) {
        when (viewEvent) {
            NewOrderEvent.ResetAction -> onResetAction()
            NewOrderEvent.OnCreateButtonClick -> onCreateButtonClick()
            NewOrderEvent.OnBackClick -> onBackClick()
            NewOrderEvent.OnAddressClick -> onAddressClick()
            NewOrderEvent.OnArrivalDateClick -> onArrivalDateClick()
            NewOrderEvent.OnArrivalTimeClick -> onArrivalTimeClick()
            NewOrderEvent.OnCargoTypeClick -> onCargoTypeClick()
            NewOrderEvent.OnExtrasClick -> onExtrasClick()
            NewOrderEvent.OnStorageClick -> onStorageClick()
            NewOrderEvent.OnRetryClick -> getContent()
            is NewOrderEvent.OnCargoTypeChanged -> onCargoTypeChanged(viewEvent.type)
            is NewOrderEvent.OnBoxesCountChanged -> onBoxesCountChanged(viewEvent.count)
            is NewOrderEvent.OnPalletsCountChanged -> onPalletsCountChanged(viewEvent.count)
            is NewOrderEvent.OnWeightChanged -> onWeightChanged(viewEvent.weight)
            is NewOrderEvent.OnBSAddressChanged -> onBSAddressChanged(viewEvent.bsAddress)
            is NewOrderEvent.OnArrivalDateChanged -> onArrivalDateChanged(viewEvent.date)
            is NewOrderEvent.OnArrivalTimeChanged -> onArrivalTimeChanged(viewEvent.time)
            is NewOrderEvent.OnStorageChanged -> onStorageChanged(viewEvent.storage)
            is NewOrderEvent.OnExtrasChanged -> onExtrasChanged(viewEvent.extras)
            is NewOrderEvent.OnCommentChanged -> onCommentChanged(viewEvent.comment)
        }
    }

    private fun getContent() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(
                isLoading = false,
                isError = true
            )
        }) {
            viewState = viewState.copy(
                isLoading = true,
                isError = false
            )
            checkCreationAvailable()
            val extras = getExtras()
            viewState = viewState.copy(
                extras = ExtrasState(uiModel = extrasMapper.map(extras) + ExtrasUiModel.Default),
                isError = false
            )
        }
    }

    private suspend fun checkCreationAvailable() {
        val unpaidOrders = getOrderRequests().filter { it.status == OrderStatusProgress.DONE && !it.isPaid }
            .sortedBy { it.arrivalDay }
        if (unpaidOrders.isNotEmpty()) {
            getAuthTokenSyncUseCase()?.let { token ->
                viewAction = NewOrderAction.OpenCreationErrorScreen(
                    CreationErrorParameters(getPaymentUri(unpaidOrders.first().id, token))
                )
            }
        } else {
            viewState = viewState.copy(isLoading = false, isError = false)
        }
    }

    private fun onArrivalDateChanged(date: Date) {
        val formatter = SimpleDateFormat(DAY_MONTH_YEAR_FORMAT, Locale.getDefault())
        viewState = viewState.copy(
            arrivalDate = NewOrderParamState.ArrivalDateState(
                stateText = formatter.format(date),
                date = date
            )
        )
        checkPrice()
    }

    private fun onArrivalTimeChanged(time: ArrivalTime) {
        viewState = viewState.copy(arrivalTime = NewOrderParamState.ArrivalTimeState(stateText = time.range))
        checkPrice()
    }

    private fun onBoxesCountChanged(count: String) {
        viewState = viewState.copy(boxesCount = NewOrderParamState.BoxesCountState(stateText = count))
        checkPrice()
    }

    private fun onCargoTypeChanged(type: CargoType) {
        viewState = viewState.copy(
            cargoType = NewOrderParamState.CargoTypeState(
                stateText = type.text,
                cargoType = type
            )
        )
        checkPrice()
    }

    private fun onBSAddressChanged(uiModel: AddressUiModel) {
        viewState = viewState.copy(
            address = viewState.address.copy(
                stateText = uiModel.address,
                activeId = uiModel.id.toInt()
            )
        )
        checkPrice()
    }

    private fun onCommentChanged(comment: String) {
        viewState = viewState.copy(comment = NewOrderParamState.CommentState(stateText = comment))
    }

    private fun onExtrasChanged(extras: List<ExtrasUiModel>) {
        viewState = viewState.copy(
            extras = ExtrasState(
                stateText = extras.joinToString(CommonConstants.Helpers.COMMA) { it.text },
                extrasActive = extras,
                uiModel = viewState.extras.uiModel
            )
        )
        checkPrice()
    }

    private fun onPalletsCountChanged(count: String) {
        viewState = viewState.copy(palletsCount = NewOrderParamState.PalletsCountState(stateText = count))
        checkPrice()
    }

    private fun onStorageChanged(storage: RouteStorageModel) {
        viewState = viewState.copy(
            storage = NewOrderParamState.StorageState(stateText = storage.name, storage = storage)
        )
        checkPrice()
    }

    private fun onWeightChanged(weight: String) {
        viewState = viewState.copy(weight = NewOrderParamState.WeightState(stateText = weight))
        checkPrice()
    }

    private fun onCreateButtonClick() {
        launchJob {
            interactor.createNewOrder(modelMapper.map(viewState))
            viewAction = NewOrderAction.OpenSuccessScreen(getOrderDate())
        }
    }

    private fun getOrderDate(): String {
        val formatter = SimpleDateFormat(FULL_DISPLAYED_DAY_MONTH, Locale.getDefault())
        val day = viewState.arrivalDate.date?.let { formatter.format(it) }
        return buildString {
            append(resourceInteractor.getString(R.string.new_order_created_text))
            append(day + SPACER + viewState.arrivalTime.stateText.lowercase())
        }
    }

    private fun onBackClick() {
        viewAction = NewOrderAction.OpenPreviousScreen
    }

    private fun onAddressClick() {
        viewAction = NewOrderAction.OpenAddressScreen
    }

    private fun onArrivalDateClick() {
        viewAction = NewOrderAction.OpenDateScreen
    }

    private fun onArrivalTimeClick() {
        viewAction = NewOrderAction.OpenTimeScreen
    }

    private fun onCargoTypeClick() {
        viewAction = NewOrderAction.OpenCargoTypeScreen
    }

    private fun onExtrasClick() {
        viewAction = NewOrderAction.OpenExtrasScreen
    }

    private fun onStorageClick() {
        viewAction = NewOrderAction.OpenStorageScreen
    }

    private fun checkPrice() {
        priceJob?.cancel()
        when {
            isFieldsFilled() -> {
                priceJob = launchJob {
                    val price = interactor.getNewOrderPrice(modelMapper.map(viewState))
                    viewState = viewState.copy(
                        createButton = NewOrderParamState.CreateButtonState(
                            subtitle = buildString {
                                append(
                                    resourceInteractor.getString(R.string.new_order_price) + SPACER +
                                        price.toStringWithEnding(RUBBLES)
                                )
                            },
                            isEnabled = true
                        )
                    )
                }
            }
            viewState.createButton.isEnabled -> {
                viewState = viewState.copy(
                    createButton = NewOrderParamState.CreateButtonState(
                        isEnabled = false
                    )
                )
            }
        }
    }

    private fun isFieldsFilled() = viewState.address.activeId != null && viewState.arrivalTime.stateText.isNotEmpty() &&
        viewState.extras.extrasActive.isNotEmpty() && viewState.arrivalDate.stateText.isNotEmpty() &&
        viewState.cargoType.stateText.isNotEmpty() && viewState.storage.storage?.id != null &&
        viewState.weight.stateText.isNotEmpty() && viewState.boxesCount.stateText.isNotEmpty() &&
        viewState.palletsCount.stateText.isNotEmpty()

}
