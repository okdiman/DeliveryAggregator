package orderdetails.root.presentation.compose.model

import root.domain.model.status.OrderStatusProgress
import java.math.BigDecimal

data class OrderDetailsUiModel(
    val id: Long,
    val contractorName: String?,
    val contractorPhone: String?,
    val arrivalTime: String,
    val status: OrderStatusProgress,
    val deliveryDate: String?,
    val delivery: OrderDeliveryUiModel?,
    val load: OrderLoadUiModel?,
    val palletCount: String,
    val deliveryAddress: String,
    val isPaid: Boolean,
    val price: BigDecimal,
) {
    val isLoadedOrDelivered = (load != null && delivery != null)

    val canPay = (!isPaid && status == OrderStatusProgress.DONE)

    companion object {
        val Default =
            OrderDetailsUiModel(
                id = 0,
                load = null,
                delivery = null,
                deliveryDate = "",
                arrivalTime = "",
                deliveryAddress = "",
                status = OrderStatusProgress.CREATED,
                palletCount = "",
                contractorName = "",
                contractorPhone = "",
                isPaid = false,
                price = BigDecimal("0.00"),
            )
    }
}
