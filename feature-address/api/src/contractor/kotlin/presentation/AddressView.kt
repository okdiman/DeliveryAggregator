package presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import presentation.model.AddressUiModel
import theme.Theme

@Composable
fun AddressView(model: AddressUiModel) {
    Text(
        text = model.address,
        style = Theme.fonts.regular
    )
}