package root.presentation.compose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modifiers.advancedShadow
import root.presentation.compose.model.OrderUiModel
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
internal fun OrderView(
    modifier: Modifier = Modifier,
    index: Int,
    model: OrderUiModel,
    onClick: (id: Long, index: Int) -> Unit,
) {
    Card(
        modifier = modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .advancedShadow(
                alpha = 0.1f,
                cornersRadius = 20.dp,
                shadowBlurRadius = 20.dp
            )
            .clip(Theme.shapes.bigCard)
            .background(Color.White)
            .clickable { onClick(model.id, index) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            OrderTitleView(model)
            OrderDepartureInfoView(model)
            OrderConnectionView()
            OrderDeliveryInfoView(model, index)
        }
    }
}

@Composable
private fun OrderTitleView(model: OrderUiModel) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = buildString {
                    append(stringResource(id = R.string.common_order_number) + model.id.toString())
                },
                style = Theme.fonts.bold.copy(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = model.arrivalDate,
                style = Theme.fonts.regular
            )
        }
        val statusText = model.statusText
        if (statusText != null) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(Theme.shapes.textFields)
                    .background(Theme.colors.hintBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    text = stringResource(id = statusText),
                    style = Theme.fonts.regular
                )
            }
        }
    }
}

@Composable
private fun OrderDepartureInfoView(model: OrderUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(Theme.shapes.roundedButton)
                .background(Theme.colors.departureImageBackgroundColor.copy(alpha = 0.08f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.route_departure_address_ic),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.common_departure_address),
                style = Theme.fonts.regular.copy(
                    color = Theme.colors.textPrimaryColor.copy(
                        alpha = 0.7f
                    )
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = model.departureAddress,
                style = Theme.fonts.bold
            )
        }
    }
}

@Composable
private fun OrderConnectionView() {
    Spacer(modifier = Modifier.height(5.dp))
    Image(
        modifier = Modifier.padding(start = 16.dp),
        painter = painterResource(id = R.drawable.route_connection_ic),
        contentDescription = null
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
private fun OrderDeliveryInfoView(model: OrderUiModel, index: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(Theme.shapes.roundedButton)
                .background(Theme.colors.deliveryImageBackgroundColor.copy(alpha = 0.08f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.route_delivery_address_ic),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.common_delivery_adddress),
                style = Theme.fonts.regular.copy(
                    color = Theme.colors.textPrimaryColor.copy(
                        alpha = 0.7f
                    )
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = model.deliveryAddress,
                style = Theme.fonts.bold
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(34.dp)
                .clip(Theme.shapes.roundedButton)
                .background(Theme.colors.hintBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(text = (index + 1).toString(), style = Theme.fonts.regular)
        }
    }
}