package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R

data class DeliveryAggregatorFonts(
    val regular: TextStyle = baseFontStyle(FontWeight.Normal),
    val semiBold: TextStyle = baseFontStyle(FontWeight.SemiBold),
    val bold: TextStyle = baseFontStyle(FontWeight.Bold)
)

private fun baseFontStyle(
    weight: FontWeight = FontWeight.Normal,
): TextStyle = TextStyle(
    fontWeight = weight,
    fontFamily = sfUiDisplay,
    color = palette.textPrimaryColor,
    fontSize = 16.sp,
    platformStyle = platformTextStyle,
    lineHeightStyle = lineHeightStyle
)

@Suppress("DEPRECATION")
private val platformTextStyle = PlatformTextStyle(includeFontPadding = false)

private val lineHeightStyle = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Proportional,
    trim = LineHeightStyle.Trim.None,
)

val sfUiDisplay = FontFamily(
    Font(R.font.sf_ui_display_regular),
    Font(R.font.sf_ui_display_semibold, weight = FontWeight.SemiBold),
    Font(R.font.sf_ui_display_bold, weight = FontWeight.Bold)
)

val fonts = DeliveryAggregatorFonts()

val LocalFontProvider =
    staticCompositionLocalOf<DeliveryAggregatorFonts> { error("No default implementation") }