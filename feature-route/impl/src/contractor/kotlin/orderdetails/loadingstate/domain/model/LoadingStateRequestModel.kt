package orderdetails.loadingstate.domain.model

import extras.domain.ExtrasModel

class LoadingStateRequestModel(
    val images: List<String>,
    val boxes: Int,
    val pallets: Int,
    val extras: List<ExtrasModel>,
    val cargoType: String
)