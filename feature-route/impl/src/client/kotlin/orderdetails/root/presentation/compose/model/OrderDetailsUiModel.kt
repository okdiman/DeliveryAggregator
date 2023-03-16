package orderdetails.root.presentation.compose.model

import root.domain.model.status.OrderStatusProgress

data class OrderDetailsUiModel(
    val id: Long,
    val contractorName: String?,
    val contractorPhone: String?,
    val arrivalTime: String,
    val status: OrderStatusProgress,
    val delivery: OrderDeliveryUiModel?,
    val load: OrderLoadUiModel?,
    val palletCount: String,
    val deliveryAddress: String,
    val isPaid: Boolean,
    val price: Int,
) {
    val isLoadedOrDelivered: Boolean
        get() = (load != null && delivery != null)

    companion object {
        val Default =
            OrderDetailsUiModel(
                id = 0,
                load = null,
                delivery = null,
                arrivalTime = "",
                deliveryAddress = "",
                status = OrderStatusProgress.CREATED,
                palletCount = "",
                contractorName = "",
                contractorPhone = "",
                isPaid = false,
                price = 0,
            )
    }
}
