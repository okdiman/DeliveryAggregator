package flow

import departure.root.presentation.compose.DepartureScreen
import editing.presentation.compose.EditProfileScreen
import navigation.NavigationTree
import notifications.presentation.compose.NotificationsScreen
import offer.presentation.compose.OfferScreen
import presentation.EditProfileParameters
import presentation.TransportProfileParameters
import root.presentation.compose.ProfileScreen
import root.presentation.compose.RouteScreen
import ru.alexgladkov.odyssey.compose.extensions.customNavigation
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import tabs.BottomConfiguration
import tabs.BottomNavigationTab
import transport.presentation.compose.TransportProfileScreen
import trinity_monsters.wildberries_delivery_aggregator.navigation.R
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

fun RootComposeBuilder.mainFlow() {
    customNavigation(
        name = NavigationTree.Main.MainFlow.name,
        tabsNavModel = BottomConfiguration()
    ) {
        tab(
            BottomNavigationTab(
                title = R_core.string.route,
                icon = R.drawable.route_ic
            )
        ) {
            screen(NavigationTree.Main.Routes.name) {
                RouteScreen()
            }
        }
        tab(
            BottomNavigationTab(
                title = R_core.string.profile,
                icon = R.drawable.profile_ic
            )
        ) {
            screen(NavigationTree.Main.Profile.name) {
                ProfileScreen()
            }
        }
    }
    screen(NavigationTree.Profile.Offer.name) {
        OfferScreen()
    }
    screen(NavigationTree.Profile.Edit.name) { parameters ->
        EditProfileScreen(parameters as EditProfileParameters)
    }
    screen(NavigationTree.Profile.Transport.name) { parameters ->
        TransportProfileScreen(parameters as TransportProfileParameters)
    }
    screen(NavigationTree.Profile.DepartureAddress.name) {
        DepartureScreen()
    }
    screen(NavigationTree.Routes.Notifications.name) {
        NotificationsScreen()
    }
}