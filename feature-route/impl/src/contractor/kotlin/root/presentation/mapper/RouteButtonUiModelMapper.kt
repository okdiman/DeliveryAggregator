package root.presentation.mapper

import root.presentation.compose.model.RouteButtonUiModel
import utils.ext.asPriceInRubles
import java.math.BigDecimal

class RouteButtonUiModelMapper {

    fun map(price: BigDecimal, distance: Double) = RouteButtonUiModel(
        text = buildString {
            append(price.asPriceInRubles())
            append(" / ")
            append(distance, DISTANCE_ENDING)
        }
    )

    private companion object {
        const val DISTANCE_ENDING = " км"
    }
}
