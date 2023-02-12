package orderdetails.loadingstate.presentation.compose.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
internal fun OrderPhotoHintView() {
    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = stringResource(id = R.string.loading_hint),
        style = Theme.fonts.regular
    )
}