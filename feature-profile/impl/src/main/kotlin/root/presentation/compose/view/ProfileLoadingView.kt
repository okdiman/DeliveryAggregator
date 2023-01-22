package root.presentation.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import modifiers.shimmerEffect
import root.presentation.compose.constants.ProfileUiConstants.ACTION_TEXT_FIELDS_COUNT
import root.presentation.compose.constants.ProfileUiConstants.INFO_TEXT_FIELDS_COUNT
import theme.Theme

@Composable
internal fun ProfileLoadingView() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 54.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(58.dp)
                    .clip(Theme.shapes.textFields)
                    .shimmerEffect()
            )
        }
        repeat(INFO_TEXT_FIELDS_COUNT) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 12.dp)
                        .height(20.dp)
                        .clip(Theme.shapes.textFields)
                        .shimmerEffect()
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Divider(modifier = Modifier.shimmerEffect(), thickness = 1.dp)
        }
        items(ACTION_TEXT_FIELDS_COUNT) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(20.dp)
                    .clip(Theme.shapes.textFields)
                    .shimmerEffect()
            )
        }
    }
}