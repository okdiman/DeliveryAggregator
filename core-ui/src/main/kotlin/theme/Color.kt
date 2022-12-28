package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class DeliveryAggregatorColors(
    val textPrimaryColor: Color,
    val textSecondaryColor: Color,
    val textThirdColor: Color,
    val textFourthColor: Color,
    val hintBackgroundColor: Color,
    val disabledTextColor: Color,
    val dividerColor: Color,
    val hintColor: Color,
    val errorColor: Color
)

val palette = DeliveryAggregatorColors(
    textPrimaryColor = Color(0xFF1A1A1A),
    textSecondaryColor = Color.White,
    textThirdColor = Color(0xFF111111),
    textFourthColor = Color(0xFF979797),
    dividerColor = Color(0xFFD9D9D9),
    hintBackgroundColor = Color(0xFFF7F7F7),
    disabledTextColor = Color(0xFFB3B3B3),
    hintColor = Color(0xFFB3B3B3),
    errorColor = Color(0xFFFF3B30)
)

val LocalColorProvider =
    staticCompositionLocalOf<DeliveryAggregatorColors> { error("No default implementation") }