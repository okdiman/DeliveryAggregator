package theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun DeliveryAggregatorTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalShapeProvider provides shapes,
        LocalColorProvider provides palette,
        content = content
    )
}

object Theme {
    val colors: DeliveryAggregatorColors
        @Composable
        get() = LocalColorProvider.current
    val shapes: DeliveryAggregatorShapes
        @Composable
        get() = LocalShapeProvider.current
}