package modifiers

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import theme.Theme

fun Modifier.shimmerEffect(duration: Int = 1000): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -size.width.toFloat(),
        targetValue = size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(duration)
        )
    )

    background(
        brush = Brush.horizontalGradient(
            colors = listOf(
                Theme.colors.hintBackgroundColor,
                Theme.colors.textSecondaryColor,
                Theme.colors.hintBackgroundColor,
            ),
            startX = startOffsetX,
            endX = startOffsetX + size.width.toFloat()
        )
    ).onGloballyPositioned {
        size = it.size
    }
}