package orderdetails.loadingstate.domain.model

class LoadingStateRequestModel(
    val images: List<String>,
    val boxes: Int,
    val pallets: Int,
    val extras: List<Long>,
    val cargoType: String
)