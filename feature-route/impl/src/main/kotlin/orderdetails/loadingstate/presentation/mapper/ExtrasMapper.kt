package orderdetails.loadingstate.presentation.mapper

import orderdetails.loadingstate.presentation.compose.model.OrderLoadingExtrasUiModel
import orderdetails.root.domain.model.extras.OrderDetailsExtrasModel
import utils.CommonConstants

class ExtrasUiMapper {
    fun map(models: List<OrderDetailsExtrasModel>) = models.map {
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