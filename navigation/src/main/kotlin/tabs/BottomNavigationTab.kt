package tabs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabConfiguration
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabItem
import theme.sfUiDisplay

class BottomNavigationTab(
    @StringRes private val title: Int,
    @DrawableRes private val icon: Int
) : TabItem() {
    override val configuration: TabConfiguration
        @Composable
        get() = TabConfiguration(
            title = stringResource(id = title),
            selectedIcon = painterResource(id = icon),
            unselectedIcon = painterResource(id = icon),
            titleStyle = TextStyle.Default.copy(
                fontSize = 12.sp,
                fontFamily = sfUiDisplay,
                fontWeight = FontWeight.SemiBold
            )
        )
}