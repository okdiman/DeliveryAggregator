package orderdetails.presentation.compose.model

import orderdetails.domain.model.status.OrderDetailsStatusProgress

data class OrderDetailsUiModel(
    val id: Long,
    val index: String,
    val clientName: String,
    val clientPhone: String,
    val status: OrderDetailsStatusProgress,
    val deliveryDate: String,
    val deliveryTime: String,
    val palletCount: String,
    val deliveryAddress: String
) {
    companion object {
        val Default =
            OrderDetailsUiModel(
                id = 0,
                deliveryDate = "",
                deliveryAddress = "",
                deliveryTime = "",
                status = OrderDetailsStatusProgress.CREATED,
                palletCount = "",
                index = "",
                clientName = "",
                clientPhone = ""
            )
    }
}