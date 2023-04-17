package root.presentation.compose.view

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
internal fun PlaceholderView(
    modifier: Modifier = Modifier,
    @StringRes placeHolderTitle: Int,
    @StringRes placeHolderSubtitle: Int,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.weight(0.7f),
            painter = painterResource(R.drawable.route_placeholder_ic),
            contentDescription = null
        )
        Text(
            text = stringResource(placeHolderTitle),
            textAlign = TextAlign.Center,
            style = Theme.fonts.bold.copy(fontSize = 20.sp),
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 42.dp),
            text = stringResource(placeHolderSubtitle),
            textAlign = TextAlign.Center,
            style = Theme.fonts.regular.copy(color = Theme.colors.darkLabelColor),
        )
    }
}
