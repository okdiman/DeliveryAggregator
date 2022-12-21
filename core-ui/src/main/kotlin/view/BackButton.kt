package view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.padding(start = 4.dp),
        onClick = { onClick() }) {
        Icon(painter = painterResource(id = R.drawable.back_ic), contentDescription = "")
    }
}