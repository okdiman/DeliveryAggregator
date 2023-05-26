package orderdetails.loadingstate.presentation.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import view.BackButton

@Composable
internal fun OrderStateTitle(title: String, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        BackButton { onBackClick() }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
    }
}