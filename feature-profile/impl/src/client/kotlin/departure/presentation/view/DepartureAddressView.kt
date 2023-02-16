package departure.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import departure.presentation.model.DepartureAddressUiModel
import theme.Theme

@Composable
internal fun DepartureAddressView(model: DepartureAddressUiModel) {
    Text(
        text = model.address,
        style = Theme.fonts.regular
    )
    if (model.comment.isNotEmpty()) {
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = model.comment,
            style = Theme.fonts.regular.copy(color = Theme.colors.textPrimaryColor.copy(alpha = 0.7f))
        )
    }
}