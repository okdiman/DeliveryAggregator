package offer.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import offer.presentation.viewmodel.model.OfferEvent
import offer.presentation.viewmodel.model.OfferState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_auth.impl.R
import view.BackButton

@Composable
fun OfferView(viewState: OfferState, eventHandler: (OfferEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        BackButton { eventHandler(OfferEvent.OnBackClick) }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.offer),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = viewState.offer,
            style = Theme.fonts.regular.copy(fontSize = 16.sp)
        )
    }
}