package orderdetails.presentation.compose.model

data class OrderDetailsUiModel(
    val id: Long,
    //    val index: Int,
    //    val clientName: String,
    //    val clientPhone: String,
    val deliveryDate: String,
    val deliveryTime: String,
    //    val cargoType: String,
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
                palletCount = ""
            )
    }
}