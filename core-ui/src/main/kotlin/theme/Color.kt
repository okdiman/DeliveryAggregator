package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class DeliveryAggregatorColors(
    val textPrimaryColor: Color,
    val textSecondaryColor: Color,
    val hintColor: Color,
)

val palette = DeliveryAggregatorColors(
    textPrimaryColor = Color(0xFF1A1A1A),
    textSecondaryColor = Color.White,
    hintColor = Color(0xFFB3B3B3),
)

val LocalColorProvider =
    staticCompositionLocalOf<DeliveryAggregatorColors> { error("No default implementation") }