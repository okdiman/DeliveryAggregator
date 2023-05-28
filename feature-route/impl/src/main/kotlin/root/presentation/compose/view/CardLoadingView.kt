package root.presentation.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import modifiers.shimmerEffect
import theme.Theme

@Composable
internal fun RouteLoadingView(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(16.dp))
    repeat(2) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(200.dp)
                .clip(Theme.shapes.bigCard)
                .shimmerEffect()
        )
    }
}