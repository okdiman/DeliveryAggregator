package neworder.storage.presentation.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import modifiers.shimmerEffect
import neworder.storage.presentation.compose.constants.NewOrderStorageUiConstants.STORAGE_ITEMS_COUNT
import theme.Theme

@Composable
internal fun NewOrderStorageLoadingView() {
    repeat(STORAGE_ITEMS_COUNT) {
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