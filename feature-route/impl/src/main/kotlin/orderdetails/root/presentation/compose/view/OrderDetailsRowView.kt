package orderdetails.root.presentation.compose.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.Theme

@Composable
internal fun OrderDetailsRowView(title: String, info: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(end = 8.dp),
            text = title,
            style = Theme.fonts.regular.copy(color = Theme.colors.textPrimaryColor.copy(alpha = 0.7f))
        )
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp),
            text = info,
            style = Theme.fonts.regular,
            textAlign = TextAlign.End
        )
    }
}
