package orderchanges.presentation.viewmodel.model

import orderchanges.presentation.compose.model.OrderChangesUiModel

data class ConfirmOrderChangesState(
    val orderId: Long = 0,
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val isConfirming: Boolean = false,
    val isRefreshing: Boolean = false,
    val changes: OrderChangesUiModel? = null,
)
