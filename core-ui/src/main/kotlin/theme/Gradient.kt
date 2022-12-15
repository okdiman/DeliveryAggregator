package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class DeliveryAggregatorGradients(
    val actionButtonGradient: Brush
)

val gradients = DeliveryAggregatorGradients(
    actionButtonGradient = Brush.horizontalGradient(
        listOf(
            Color(0xFF4A82D0),
            Color(0xFF4B83CA),
            Color(0xFF4D88B8),
            Color(0xFF518E9B),
            Color(0xFF579872),
            Color(0xFF5CA14A),
            Color(0xFF67A649),
            Color(0xFF84B445),
            Color(0xFFB4C93F),
            Color(0xFFF0E537)
        )
    )
)

val LocalGradientProvider =
    staticCompositionLocalOf<DeliveryAggregatorGradients> { error("No default implementation") }