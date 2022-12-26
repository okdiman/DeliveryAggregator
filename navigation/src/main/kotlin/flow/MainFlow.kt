package flow

import androidx.compose.material.Text
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.bottomNavigation
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import tabs.BottomConfiguration
import tabs.BottomNavigationTab
import trinity_monsters.wildberries_delivery_aggregator.navigation.R

fun RootComposeBuilder.mainFlow() {
    bottomNavigation(
        name = NavigationTree.Main.MainFlow.name,
        tabsNavModel = BottomConfiguration()
    ) {
        tab(
            BottomNavigationTab(
                title = R.string.route,
                icon = R.drawable.route_ic
            )
        ) {
            screen(NavigationTree.Main.Routes.name) {
                Text(text = "routes")
            }
        }
        tab(
            BottomNavigationTab(
                title = R.string.profile,
                icon = R.drawable.profile_ic
            )
        ) {
            screen(NavigationTree.Main.Profile.name) {
                Text(text = "profile")
            }
        }
    }
}