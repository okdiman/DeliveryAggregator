package view

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import trinity_monsters.delivery_aggregator.core_ui.R

@Composable
fun BackButtonView(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.size(24.dp),
        onClick = { onClick() }) {
        Icon(painter = painterResource(id = R.drawable.back_ic), contentDescription = "")
    }
}