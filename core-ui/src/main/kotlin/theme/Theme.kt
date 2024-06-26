package theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun DeliveryAggregatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        CompositionLocalProvider(
            LocalShapeProvider provides shapes,
            LocalColorProvider provides palette,
            LocalGradientProvider provides gradients,
            LocalFontProvider provides fonts,
            LocalFilterChipColorProvider provides filterChipColors,
            content = content
        )
    }
}

object Theme {
    val colors: DeliveryAggregatorColors
        @Composable
        get() = LocalColorProvider.current
    val shapes: DeliveryAggregatorShapes
        @Composable
        get() = LocalShapeProvider.current
    val gradients: DeliveryAggregatorGradients
        @Composable
        get() = LocalGradientProvider.current
    val fonts: DeliveryAggregatorFonts
        @Composable
        get() = LocalFontProvider.current
    val filterChipColors: DeliveryAggregateChipColors
        @Composable
        get() = LocalFilterChipColorProvider.current
}
