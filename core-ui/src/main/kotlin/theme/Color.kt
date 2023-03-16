package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class DeliveryAggregatorColors(
    val textPrimaryColor: Color,
    val textSecondaryColor: Color,
    val textThirdColor: Color,
    val textFourthColor: Color,
    val darkLabelColor: Color,
    val hintBackgroundColor: Color,
    val disabledTextColor: Color,
    val disabledButtonColor: Color,
    val dividerColor: Color,
    val hintColor: Color,
    val radioButtonColor: Color,
    val errorColor: Color,
    val badgeBackgroundColor: Color,
    val deliveryImageBackgroundColor: Color,
    val departureImageBackgroundColor: Color,
    val selectionTextColor: Color,
    val chipSelectedColor: Color,
    val chipUnselectedColor: Color,
    val calendarBackgroundColor: Color
)

val palette = DeliveryAggregatorColors(
    textPrimaryColor = Color(0xFF1A1A1A),
    textSecondaryColor = Color.White,
    textThirdColor = Color(0xFF111111),
    textFourthColor = Color(0xFF979797),
    darkLabelColor = Color(0xFF5F5F5F),
    dividerColor = Color(0xFFD9D9D9),
    hintBackgroundColor = Color(0xFFF7F7F7),
    disabledTextColor = Color(0xFFB3B3B3),
    disabledButtonColor = Color(0xFFECF2ED),
    hintColor = Color(0xFFB3B3B3),
    errorColor = Color(0xFFFF3B30),
    radioButtonColor = Color(0xFF589A6E),
    badgeBackgroundColor = Color(0xFFE36B2B),
    deliveryImageBackgroundColor = Color(0xFFEB9583),
    departureImageBackgroundColor = Color(0xFF63BBC3),
    selectionTextColor = Color(0xFF5B9F57),
    chipSelectedColor = Color(0xFF3579F5),
    chipUnselectedColor = Color(0xFFF7F7F7),
    calendarBackgroundColor = Color(0xFFECF2ED)
)

val LocalColorProvider =
    staticCompositionLocalOf<DeliveryAggregatorColors> { error("No default implementation") }
