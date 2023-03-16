package orderchanges.presentation.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import modifiers.shimmerEffect
import orderchanges.presentation.compose.constants.ConfirmOrderChangesUiConstants
import theme.Theme

@Composable
internal fun ConfirmOrderChangesLoadingView() {
    Row(modifier = Modifier.fillMaxWidth()) {
        ColumnLoadingView(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(10.dp))
        ColumnLoadingView(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ColumnLoadingView(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(vertical = 8.dp)
                .height(30.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
        repeat(ConfirmOrderChangesUiConstants.CHANGED_DETAIL_FIELDS_COUNT) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(65.dp)
                    .clip(Theme.shapes.textFields)
                    .shimmerEffect()
            )
        }
    }
}
