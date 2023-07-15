package view

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

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