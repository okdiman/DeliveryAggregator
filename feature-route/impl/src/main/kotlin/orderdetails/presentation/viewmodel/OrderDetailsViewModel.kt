package orderdetails.presentation.viewmodel

import BaseViewModel
import additionalInfo.presentation.AdditionalInfoParameters
import coroutines.AppDispatchers
import orderdetails.domain.GetOrderDetailsUseCase
import orderdetails.presentation.OrderDetailsParameters
import orderdetails.presentation.mapper.OrderDetailsMapper
import orderdetails.presentation.viewmodel.model.OrderDetailsAction
import orderdetails.presentation.viewmodel.model.OrderDetailsEvent
import orderdetails.presentation.viewmodel.model.OrderDetailsState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OrderDetailsViewModel(
    private val parameters: OrderDetailsParameters
) : BaseViewModel<OrderDetailsState, OrderDetailsAction, OrderDetailsEvent>(
    initialState = OrderDetailsState()
), KoinComponent {

    private val getOrderDetails by inject<GetOrderDetailsUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val mapper by inject<OrderDetailsMapper>()
    private lateinit var additionalInfoParameters: AdditionalInfoParameters

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: OrderDetailsEvent) {
        when (viewEvent) {
            OrderDetailsEvent.OnAdditionalInfoClick -> onAdditionalInfoClick()
            OrderDetailsEvent.OnBackClick -> onBackClick()
            OrderDetailsEvent.OnDeliveryStateClick -> onDeliveryStateClick()
            OrderDetailsEvent.OnLoadingStateClick -> onLoadingStateClick()
            OrderDetailsEvent.OnRetryClick -> getContent()
            OrderDetailsEvent.ResetAction -> onResetAction()
        }
    }

    fun getAdditionalInfoParams() = additionalInfoParameters

    private fun getContent() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isLoading = false, isError = true)
        }) {
            viewState = viewState.copy(isLoading = true, isError = false)
            val order = getOrderDetails(parameters.id)
            additionalInfoParameters = AdditionalInfoParameters(
                marketplace = order.order.marketplace.name,
                boxesCount = order.order.boxes.toString(),
                comment = order.order.comment,
                weight = order.order.weight.toString(),
                //FIXME
                organization = order.order.storage.name,
                additionalOptions = ""
            )
            viewState = viewState.copy(isLoading = false, uiModel = mapper.map(order))
        }
    }

    private fun onAdditionalInfoClick() {
        viewAction = OrderDetailsAction.OpenAdditionalInfo
    }

    private fun onBackClick() {
        viewAction = OrderDetailsAction.OpenPreviousScreen
    }

    private fun onDeliveryStateClick() {
        viewAction = OrderDetailsAction.OpenDeliveryStateScreen(parameters.id)
    }

    private fun onLoadingStateClick() {
        viewAction = OrderDetailsAction.OpenLoadingStateScreen(parameters.id)
    }
}