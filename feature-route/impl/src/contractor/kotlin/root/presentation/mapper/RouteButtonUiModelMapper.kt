package root.presentation.mapper

import root.presentation.compose.model.RouteButtonUiModel
import utils.ext.toStringWithEnding

class RouteButtonUiModelMapper {
    fun map(price: Int, distance: Double) = RouteButtonUiModel(
        text = buildString {
            append(
                price.toStringWithEnding(PRICE_ENDING) +
                    distance.toStringWithEnding(DISTANCE_ENDING)
            )
        }
    )

    private companion object {
        const val PRICE_ENDING = "₽ / "
        const val DISTANCE_ENDING = " км"
    }
}