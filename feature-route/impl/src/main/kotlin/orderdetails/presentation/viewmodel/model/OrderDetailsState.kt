package orderdetails.presentation.viewmodel.model

import orderdetails.presentation.compose.model.OrderDetailsUiModel

data class OrderDetailsState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val uiModel: OrderDetailsUiModel = OrderDetailsUiModel.Default
)