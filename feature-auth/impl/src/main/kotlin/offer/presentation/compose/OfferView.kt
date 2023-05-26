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
import trinity_monsters.delivery_aggregator.feature_auth.impl.R
import view.BackButton

@Composable
internal fun OfferView(viewState: OfferState, eventHandler: (OfferEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        BackButton { eventHandler(OfferEvent.OnBackClick) }
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = stringResource(id = R.string.offer),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        //FIXME Когда будет нормальная оферта, надо будет убрать подзаголовок
        Text(
            text = stringResource(id = R.string.offer_subtitle), style = Theme.fonts.bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = viewState.offer, style = Theme.fonts.regular
        )
    }
}