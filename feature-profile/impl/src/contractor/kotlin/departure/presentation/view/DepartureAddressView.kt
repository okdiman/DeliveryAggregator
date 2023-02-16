package departure.presentation.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import departure.presentation.model.DepartureAddressUiModel
import theme.Theme

@Composable
internal fun DepartureAddressView(model: DepartureAddressUiModel) {
    Text(
        text = model.address,
        style = Theme.fonts.regular
    )
}