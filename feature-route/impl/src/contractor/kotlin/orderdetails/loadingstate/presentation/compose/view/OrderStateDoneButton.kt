package orderdetails.loadingstate.presentation.compose.view

import ScrollScreenActionButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
internal fun OrderStateDoneButton(
    onPositioned: (Float) -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            text = stringResource(R.string.loading_step_done),
            onPositioned = onPositioned
        ) { onClick() }
    }
}
