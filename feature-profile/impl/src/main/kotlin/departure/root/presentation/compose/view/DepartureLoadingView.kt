package departure.root.presentation.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import departure.root.presentation.compose.constants.DepartureUiConstants.ADDRESSES_MAX_COUNT
import modifiers.shimmerEffect
import theme.Theme

@Composable
internal fun DepartureLoadingView() {
    repeat(ADDRESSES_MAX_COUNT) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, start = 8.dp, end = 8.dp, top = 12.dp)
                .height(22.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
    }
}