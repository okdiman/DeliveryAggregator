package view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import theme.DeliveryAggregatorTheme
import theme.Theme
import view.ProgressBarConstants.DefaultAnimationDelay
import view.ProgressBarConstants.DefaultAnimationDuration
import view.ProgressBarConstants.DefaultBallCount
import view.ProgressBarConstants.DefaultMaxBallDiameter
import view.ProgressBarConstants.DefaultMinBallDiameter
import view.ProgressBarConstants.DefaultSpacing
import view.ProgressBarConstants.DefaultStartDelay

@Suppress("LongParameterList")
@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    gradient: Brush = Theme.gradients.progressBarGradient,
    animationDuration: Int = DefaultAnimationDuration,
    animationDelay: Int = DefaultAnimationDelay,
    startDelay: Int = DefaultStartDelay,
    ballCount: Int = DefaultBallCount,
    maxBallDiameter: Dp = DefaultMaxBallDiameter,
    minBallDiameter: Dp = DefaultMinBallDiameter,
    spacing: Dp = DefaultSpacing
) {
    val transition = rememberInfiniteTransition(label = "")
    val duration = startDelay + animationDuration + animationDelay

    val diameters = arrayListOf<Float>().apply {
        for (i in 0 until ballCount) {
            val delay = startDelay + (animationDelay / (ballCount - 1)) * i
            val diameter by transition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = duration
                        0f at delay with FastOutSlowInEasing
                        1f at (animationDuration / 2) + delay with FastOutSlowInEasing
                        0f at animationDuration + delay
                        0f at duration
                    },
                ),
                label = ""
            )
            add(diameter)
        }
    }

    val width = (maxBallDiameter + spacing) * ballCount - spacing

    ProgressIndicator(modifier, width, maxBallDiameter) {
        drawIndeterminateBallPulseIndicator(
            maxDiameter = maxBallDiameter.toPx(),
            diameter = diameters.map { lerp(minBallDiameter, maxBallDiameter, it).toPx() },
            spacing = spacing.toPx(),
            gradient = gradient
        )
    }
}

private fun DrawScope.drawIndeterminateBallPulseIndicator(
    maxDiameter: Float,
    diameter: List<Float>,
    spacing: Float,
    gradient: Brush
) {
    for (i in diameter.indices) {
        val x = i * (maxDiameter + spacing) + maxDiameter / 2
        drawCircle(
            brush = gradient,
            radius = diameter[i] / 2,
            center = Offset(x, size.height / 2)
        )
    }
}

@Composable
private fun ProgressIndicator(
    modifier: Modifier,
    width: Dp,
    height: Dp,
    onDraw: DrawScope.() -> Unit
) {
    Canvas(
        modifier = modifier
            .progressSemantics()
            .size(width, height)
            .focusable(),
        onDraw = onDraw
    )
}

private object ProgressBarConstants {
    const val DefaultAnimationDuration = 600
    const val DefaultAnimationDelay = 200
    const val DefaultStartDelay = 0
    const val DefaultBallCount = 4

    val DefaultMaxBallDiameter = 12.dp
    val DefaultMinBallDiameter = 8.dp
    val DefaultSpacing = 6.dp
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun ProgressIndicator_Preview() {
    DeliveryAggregatorTheme {
        ProgressIndicator()
    }
}
