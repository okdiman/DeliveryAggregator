package view

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import theme.DeliveryAggregatorTheme
import theme.Theme

/**
Без провайдера используются отступы, которые нельзя контролировать
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckboxView(
    checked: Boolean,
    colors: CheckboxColors,
    onCheckedChange: ((Boolean) -> Unit)? = null
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Checkbox(
            checked = checked,
            colors = colors,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CheckboxView_Preview() {
    DeliveryAggregatorTheme {
        CheckboxView(
            true,
            CheckboxDefaults.colors(
                checkedColor = Theme.colors.textPrimaryColor
            )
        )
    }
}
