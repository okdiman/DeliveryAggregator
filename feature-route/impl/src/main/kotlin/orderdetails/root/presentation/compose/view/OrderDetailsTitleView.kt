package orderdetails.root.presentation.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orderdetails.root.presentation.viewmodel.model.OrderDetailsState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import view.BackButtonView

@Composable
internal fun OrderDetailsTitleView(
    state: OrderDetailsState,
    onClickBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        BackButtonView { onClickBack() }
        if (!state.isLoading) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = buildString {
                    append(stringResource(id = R.string.common_order_number) + state.uiModel.id)
                },
                style = Theme.fonts.bold.copy(fontSize = 20.sp)
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}