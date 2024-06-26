package root.domain.model

import extras.domain.ExtrasModel

@Suppress("LongParameterList")
class NewOrderModel(
    val addressId: Long,
    val arrivalDay: String,
    val arrivalTime: String,
    val cargoType: String,
    val boxes: Int,
    val comment: String,
    val marketplaceId: Int,
    val pallets: Int,
    val storageId: Int,
    val weight: Int,
    val extras: List<ExtrasModel>
)