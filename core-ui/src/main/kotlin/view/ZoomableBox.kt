package view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.toOffset

/** контейнер, внутри которого можно зумить вью*/
@Composable
fun ZoomableBox(
    modifier: Modifier = Modifier,
    minScale: Float = 1f,
    maxScale: Float = 5f,
    doubleTapToZoomFactor: Float = 2f,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable ZoomableBoxScope.() -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    // Состояния для анимаций
    var hasDoubleTappedToZoomInOrOut by remember { mutableStateOf(false) }
    val zoomAnimatedOffsetTarget: Offset by animateOffsetAsState(offset) {
        offset = it
        hasDoubleTappedToZoomInOrOut = false
    }
    val zoomAnimatedScale: Float by animateFloatAsState(scale) {
        scale = it
        hasDoubleTappedToZoomInOrOut = false
    }

    Box(
        modifier = modifier
            .onSizeChanged { size = it }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(minScale, maxScale)
                    val maxX = (size.width * (scale - 1)) / 2
                    val minX = -maxX
                    val maxY = (size.height * (scale - 1)) / 2
                    val minY = -maxY

                    offset = Offset(
                        x = (offset.x + pan.x).coerceIn(minX, maxX),
                        y = (offset.y + pan.y).coerceIn(minY, maxY),
                    )
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(onDoubleTap = { doubleTapOffset ->
                    hasDoubleTappedToZoomInOrOut = true
                    if (scale > 1f) {
                        offset = Offset.Zero
                        scale = 1f
                    } else {
                        offset = -(doubleTapOffset - size.center.toOffset())
                        scale = doubleTapToZoomFactor
                    }
                })
            },
        contentAlignment = contentAlignment,
    ) {
        val scope = ZoomableBoxScopeImpl(
            scale = if (hasDoubleTappedToZoomInOrOut) zoomAnimatedScale else scale,
            offset = if (hasDoubleTappedToZoomInOrOut) zoomAnimatedOffsetTarget else offset,
            boxScope = this
        )
        scope.content()
    }
}

interface ZoomableBoxScope : BoxScope {
    val scale: Float
    val offset: Offset
}

private data class ZoomableBoxScopeImpl(
    override val scale: Float,
    override val offset: Offset,
    private val boxScope: BoxScope,
) : ZoomableBoxScope {
    override fun Modifier.align(alignment: Alignment): Modifier =
        with(boxScope) { align(alignment) }

    override fun Modifier.matchParentSize(): Modifier = with(boxScope) { matchParentSize() }
}
