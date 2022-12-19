package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class DeliveryAggregatorColors(
    val textPrimaryColor: Color,
    val textSecondaryColor: Color,
    val textThirdColor: Color,
    val hintBackgroundColor: Color,
    val hintColor: Color,
    val errorColor: Color
)

val palette = DeliveryAggregatorColors(
    textPrimaryColor = Color(0xFF1A1A1A),
    textSecondaryColor = Color.White,
    textThirdColor = Color(0xFF111111),
    hintBackgroundColor = Color(0xFFF7F7F7),
    hintColor = Color(0xFFB3B3B3),
    errorColor = Color(0xFFFF3B30)
)

val LocalColorProvider =
    staticCompositionLocalOf<DeliveryAggregatorColors> { error("No default implementation") }