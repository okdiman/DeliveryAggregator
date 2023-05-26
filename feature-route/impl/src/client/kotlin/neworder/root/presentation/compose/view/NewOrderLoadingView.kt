package neworder.root.presentation.compose.view

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
import neworder.root.presentation.compose.constants.NewOrderUiConstants.FIELDS_ITEMS_COUNT
import theme.Theme

@Composable
internal fun NewOrderLoadingView() {
    Spacer(modifier = Modifier.height(12.dp))
    repeat(FIELDS_ITEMS_COUNT) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 4.dp)
                .height(20.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                .height(48.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
    }
}