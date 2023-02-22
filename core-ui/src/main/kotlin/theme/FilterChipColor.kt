package theme

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SelectableChipColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Immutable
data class DeliveryAggregateChipColors(
    private val backgroundColor: Color,
    private val selectedBackgroundColor: Color,
    private val contentColor: Color,
    private val selectedContentColor: Color,
) : SelectableChipColors {

    @Composable
    override fun backgroundColor(enabled: Boolean, selected: Boolean) =
        if (selected) rememberUpdatedState(selectedBackgroundColor) else rememberUpdatedState(backgroundColor)

    @Composable
    override fun contentColor(enabled: Boolean, selected: Boolean) =
        if (selected) rememberUpdatedState(selectedContentColor) else rememberUpdatedState(contentColor)

    @Composable
    override fun leadingIconColor(enabled: Boolean, selected: Boolean) = rememberUpdatedState(Color.Unspecified)
}

val filterChipColors = DeliveryAggregateChipColors(
    backgroundColor = palette.chipUnselectedColor,
    selectedBackgroundColor = palette.chipSelectedColor,
    contentColor = palette.textFourthColor,
    selectedContentColor = Color.White,
)

val LocalFilterChipColorProvider =
    staticCompositionLocalOf<DeliveryAggregateChipColors> { error("No default implementation") }
