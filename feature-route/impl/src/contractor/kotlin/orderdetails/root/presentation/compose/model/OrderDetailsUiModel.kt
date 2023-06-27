package orderdetails.root.presentation.compose.model

import root.domain.model.status.OrderStatusProgress

data class OrderDetailsUiModel(
    val id: Long,
    val index: String,
    val clientName: String,
    val clientPhone: String,
    val status: OrderStatusProgress,
    val deliveryDate: String,
    val deliveryTime: String,
    val palletCount: String,
    val deliveryAddress: String,
    val storageAddress: String,
    val cargoType: String
) {
    companion object {
        val Default =
            OrderDetailsUiModel(
                id = 0,
                deliveryDate = "",
                deliveryAddress = "",
                deliveryTime = "",
                status = OrderStatusProgress.CREATED,
                palletCount = "",
                index = "",
                clientName = "",
                clientPhone = "",
                cargoType = "",
                storageAddress = ""
            )
    }
}