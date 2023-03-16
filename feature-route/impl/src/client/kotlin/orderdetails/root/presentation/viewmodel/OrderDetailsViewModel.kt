package orderdetails.root.presentation.viewmodel

import BaseViewModel
import additionalInfo.presentation.AdditionalInfoParameters
import coroutines.AppDispatchers
import network.domain.GetAuthTokenSyncUseCase
import neworder.payment.domain.GetPaymentUriUseCase
import orderdetails.root.domain.GetClientOrderDetailsUseCase
import orderdetails.root.presentation.OrderDetailsParameters
import orderdetails.root.presentation.mapper.OrderDetailsMapper
import orderdetails.root.presentation.viewmodel.model.OrderDetailsAction
import orderdetails.root.presentation.viewmodel.model.OrderDetailsEvent
import orderdetails.root.presentation.viewmodel.model.OrderDetailsState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OrderDetailsViewModel(
    private val parameters: OrderDetailsParameters
) : BaseViewModel<OrderDetailsState, OrderDetailsAction, OrderDetailsEvent>(
    initialState = OrderDetailsState()
), KoinComponent {

    private val getOrderDetails by inject<GetClientOrderDetailsUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val mapper by inject<OrderDetailsMapper>()
    private lateinit var additionalInfoParameters: AdditionalInfoParameters
    private val getPaymentUri by inject<GetPaymentUriUseCase>()
    private val getAuthTokenSyncUseCase by inject<GetAuthTokenSyncUseCase>()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: OrderDetailsEvent) {
        when (viewEvent) {
            OrderDetailsEvent.OnAdditionalInfoClick -> onAdditionalInfoClick()
            OrderDetailsEvent.OnBackClick -> onBackClick()
            OrderDetailsEvent.OnRetryClick -> getContent()
            OrderDetailsEvent.ResetAction -> onResetAction()
            OrderDetailsEvent.OnPayClick -> onPay()
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
                marketplace = order.details.marketplace.name,
                boxesCount = order.details.boxes.toString(),
                comment = order.details.comment,
                weight = order.details.weight.toString(),
                organization = order.details.organizationName,
                extras = order.details.extras
            )
            viewState = viewState.copy(isLoading = false, uiModel = mapper.map(order))
        }
    }

    private fun onPay() {
        getAuthTokenSyncUseCase()?.let { token ->
            viewAction = OrderDetailsAction.OpenPaymentInBrowserAndReturn(getPaymentUri(parameters.id, token))
        }
    }

    private fun onAdditionalInfoClick() {
        viewAction = OrderDetailsAction.OpenAdditionalInfo
    }

    private fun onBackClick() {
        viewAction = OrderDetailsAction.OpenPreviousScreen
    }
}
