package tabs

import androidx.compose.runtime.Composable
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.CustomNavConfiguration
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabsNavModel
import view.CustomBottomBarNavigator

class BottomConfiguration : TabsNavModel<CustomNavConfiguration>() {
    override val navConfiguration: CustomNavConfiguration
        @Composable
        get() {
            return CustomNavConfiguration {
                CustomBottomBarNavigator(
                    startScreen = NavigationTree.Main.Orders.name,
                    alternativeScreen = NavigationTree.NewOrder.Creating.name
                )
            }
        }
}