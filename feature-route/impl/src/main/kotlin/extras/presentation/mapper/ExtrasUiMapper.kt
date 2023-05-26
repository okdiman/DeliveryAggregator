package extras.presentation.mapper

import extras.presentation.model.ExtrasUiModel
import root.domain.model.extras.OrderExtrasModel
import utils.CommonConstants
import utils.ext.asPriceInRubles

class ExtrasUiMapper {
    fun map(models: List<OrderExtrasModel>) = models.map {
        ExtrasUiModel(
            id = it.id,
            text = if (it.priceDescription.isValid) {
                buildString { append(it.name + CommonConstants.Helpers.COMMA + it.priceDescription.text) }
            } else {
                buildString {
                    append(it.name + CommonConstants.Helpers.COMMA + it.price.asPriceInRubles())
                }
            }
        )
    }
}
