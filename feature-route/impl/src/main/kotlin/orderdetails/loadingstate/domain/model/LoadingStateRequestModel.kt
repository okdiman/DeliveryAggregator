package orderdetails.loadingstate.domain.model

class LoadingStateRequestModel(
    val images: ArrayList<String>,
    val boxes: Int,
    val pallets: Int,
    val extras: ArrayList<Int>,
    val cargoType: String
)