package flow

import departure.root.presentation.compose.DepartureScreen
import editing.presentation.EditProfileParameters
import editing.presentation.compose.EditProfileScreen
import navigation.NavigationTree
import notifications.presentation.compose.NotificationsScreen
import offer.presentation.compose.OfferScreen
import orderdetails.presentation.OrderDetailsParameters
import orderdetails.presentation.compose.OrderDetailsScreen
import root.presentation.compose.ProfileScreen
import root.presentation.compose.RouteScreen
import ru.alexgladkov.odyssey.compose.extensions.customNavigation
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import tabs.BottomConfiguration
import tabs.BottomNavigationTab
import transport.presentation.TransportProfileParameters
import transport.presentation.compose.TransportProfileScreen
import trinity_monsters.delivery_aggregator.navigation.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

fun RootComposeBuilder.mainFlow() {
    customNavigation(
        name = NavigationTree.Main.MainFlow.name,
        tabsNavModel = BottomConfiguration()
    ) {
        tab(
            BottomNavigationTab(
                title = R_core.string.navigation_route,
                icon = R.drawable.route_ic
            )
        ) {
            screen(NavigationTree.Main.Routes.name) {
                RouteScreen()
            }
        }
        tab(
            BottomNavigationTab(
                title = R_core.string.navigation_profile,
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
    flow(NavigationTree.Routes.RouteDetails.name) {
        screen(NavigationTree.Routes.RouteDetails.name) { parameters ->
            OrderDetailsScreen(parameters as OrderDetailsParameters)
        }
    }
}