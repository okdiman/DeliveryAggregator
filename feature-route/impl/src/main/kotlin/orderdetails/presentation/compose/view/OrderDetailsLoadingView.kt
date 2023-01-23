package orderdetails.presentation.compose.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import modifiers.shimmerEffect
import orderdetails.presentation.compose.OrderDetailsUiConstants.INFO_VIEW_COUNT
import theme.Theme

@Composable
fun OrderDetailsLoadingView() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(0.5f)
                .clip(Theme.shapes.textFields)
                .padding(bottom = 8.dp)
                .shimmerEffect()
        )
        repeat(INFO_VIEW_COUNT) {
            OrderDetailsLoadingInfoView()
        }
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(24.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        repeat(2) {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(Theme.shapes.card)
                    .shimmerEffect()
            )
        }
    }
}

@Composable
private fun OrderDetailsLoadingInfoView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(20.dp)
                .padding(end = 8.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(20.dp)
                .padding(start = 8.dp)
                .clip(Theme.shapes.textFields)
                .shimmerEffect()
        )
    }
}