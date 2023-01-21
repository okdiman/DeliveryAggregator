package additionalInfo.presentation.compose

import ActionButton
import additionalInfo.presentation.viewmodel.model.AdditionalInfoEvent
import additionalInfo.presentation.viewmodel.model.AdditionalInfoState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_route.impl.R

@Composable
internal fun AdditionalInfoView(
    state: AdditionalInfoState,
    eventHandler: (AdditionalInfoEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp, horizontal = 16.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.order_details_additional_info),
                style = Theme.fonts.bold.copy(
                    fontSize = 24.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(state.uiModels) {
            AdditionalInfoBlock(
                title = stringResource(it.title),
                text = it.text
            )
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
            ActionButton(
                modifier = Modifier.fillMaxSize(),
                textRes = R.string.additional_info_back,
                padding = PaddingValues(0.dp)
            ) { eventHandler(AdditionalInfoEvent.OnBackClickEvent) }
        }
    }
}

@Composable
private fun AdditionalInfoBlock(title: String, text: String) {
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = title,
        style = Theme.fonts.bold.copy(fontSize = 18.sp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = text,
        style = Theme.fonts.regular.copy(fontSize = 18.sp)
    )
}