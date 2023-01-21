package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import utils.LinearGradient

data class DeliveryAggregatorGradients(
    val actionButtonGradient: Brush,
    val progressBarGradient: Brush,
    val buttonBackgroundGradient: Brush
)

val gradients = DeliveryAggregatorGradients(
    actionButtonGradient = LinearGradient(
        colors = listOf(
            Color(0xFF56967D),
            Color(0xFF5B9F57),
            Color(0xFF70AB48),
            Color(0xFFA4C241)
        ),
        angleInDegrees = 9f
    ),
    progressBarGradient = LinearGradient(
        colors = listOf(
            Color(0xFF3D81D7),
            Color(0xFF6BA0E4)
        ),
        angleInDegrees = 90f
    ),
    buttonBackgroundGradient = LinearGradient(
        colors = listOf(
            Color(0x00FFFFFF),
            Color(0x99FFFFFF),
            Color(0xCCFFFFFF),
            Color(0xE6FFFFFF),
            Color(0xFFFFFFFF)
        ),
        angleInDegrees = 270f
    )
)

val LocalGradientProvider =
    staticCompositionLocalOf<DeliveryAggregatorGradients> { error("No default implementation") }