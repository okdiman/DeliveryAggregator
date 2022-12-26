package tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.BottomNavConfiguration
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabsNavModel
import theme.Theme

class BottomConfiguration : TabsNavModel<BottomNavConfiguration>() {
    override val navConfiguration: BottomNavConfiguration
        @Composable
        get() {
            return BottomNavConfiguration(
                elevation = 25.dp,
                backgroundColor = Color.White,
                unselectedColor = Theme.colors.textPrimaryColor.copy(
                    alpha = 0.3f
                ),
                selectedColor = Theme.colors.textPrimaryColor
            )
        }
}