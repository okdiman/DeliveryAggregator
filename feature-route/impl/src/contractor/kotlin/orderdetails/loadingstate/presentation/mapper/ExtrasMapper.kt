package orderdetails.loadingstate.presentation.mapper

import orderdetails.loadingstate.presentation.compose.model.OrderLoadingExtrasUiModel
import root.domain.model.extras.OrderExtrasModel
import utils.CommonConstants

class ExtrasUiMapper {
    fun map(models: List<OrderExtrasModel>) = models.map {
        OrderLoadingExtrasUiModel(
            id = it.id,
            text = if (it.priceDescription.isValid) {
                buildString { append(it.name + CommonConstants.Helpers.COMMA + it.priceDescription.text) }
            } else {
                buildString {
                    append(it.name + CommonConstants.Helpers.COMMA + it.price + CommonConstants.Helpers.RUBBLES)
                }
            }
        )
    } + OrderLoadingExtrasUiModel.Default
}
