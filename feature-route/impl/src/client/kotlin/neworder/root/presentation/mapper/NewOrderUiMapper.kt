package neworder.root.presentation.mapper

import neworder.root.presentation.viewmodel.model.NewOrderState
import root.domain.model.NewOrderModel
import utils.ext.DateFormats
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewOrderUiMapper {
    fun map(state: NewOrderState) = NewOrderModel(
        addressId = state.address.activeId ?: 0,
        arrivalDay = getDate(state.arrivalDate.date).orEmpty(),
        arrivalTime = state.arrivalTime.stateText,
        boxes = state.boxesCount.stateText.toInt(),
        comment = state.comment.stateText,
        marketplaceId = 1,
        pallets = state.palletsCount.stateText.toInt(),
        storageId = state.storage.storage?.id ?: 0,
        weight = state.weight.stateText.toInt(),
        cargoType = state.cargoType.stateText,
        extras = state.extras.extrasActive.map { it.id }
    )

    private fun getDate(date: Date?): String? {
        val remoteFormatter = SimpleDateFormat(DateFormats.YEAR_MONTH_DAY, Locale.getDefault())
        return date?.let { remoteFormatter.format(it) }
    }
}