package orderdetails.presentation.mapper

import orderdetails.presentation.compose.model.OrderDetailsUiModel
import root.domain.model.RouteOrderModel

class OrderDetailsMapper {
    fun map(model: RouteOrderModel): OrderDetailsUiModel {
//        return OrderDetailsUiModel(
//            id = model.id,
//            index = index,
//            clientName = String,
//            clientPhone = String,
//            deliveryDate = model.arrivalDay,
//            deliveryTime = model.arrivalTime,
////            cargoType = String,
//            palletCount = model.pallets.toString(),
//            deliveryAddress = model.storage.address
//        )
        return OrderDetailsUiModel.Default
    }
}