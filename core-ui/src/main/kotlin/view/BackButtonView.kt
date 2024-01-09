package view

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import theme.DeliveryAggregatorTheme
import trinity_monsters.delivery_aggregator.core_ui.R

@Composable
fun BackButtonView(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier.then(Modifier.size(24.dp)),
        onClick = { onClick() })
    {
        Icon(painter = painterResource(id = R.drawable.back_ic), contentDescription = "")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BackButtonView_Preview() {
    DeliveryAggregatorTheme {
        BackButtonView() {}
    }
}
