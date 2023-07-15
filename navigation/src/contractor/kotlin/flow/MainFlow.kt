package flow

import PdfScreenParameters
import departure.presentation.compose.DepartureScreen
import editing.presentation.EditProfileParameters
import editing.presentation.compose.EditProfileScreen
import navigation.NavigationTree
import notifications.presentation.compose.NotificationsScreen
import orderdetails.deliverystate.presentation.compose.OrderDeliveryScreen
import orderdetails.loadingstate.presentation.compose.OrderLoadingScreen
import orderdetails.root.presentation.OrderDetailsParameters
import orderdetails.root.presentation.OrderStatesParameters
import orderdetails.root.presentation.compose.OrderDetailsScreen
import pdf.presentation.compose.PdfScreen
import presentation.DeeplinkParameters
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

fun RootComposeBuilder.mainFlow(deeplinkParameters: DeeplinkParameters?) {
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
            screen(NavigationTree.Main.Routes.name) { parameters ->
                RouteScreen(parameters as? DeeplinkParameters ?: deeplinkParameters)
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
    screen(NavigationTree.Profile.Pdf.name) { parameters ->
        PdfScreen(parameters as PdfScreenParameters)
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
        screen(NavigationTree.Routes.LoadingState.name) { parameters ->
            OrderLoadingScreen(parameters as OrderStatesParameters)
        }
        screen(NavigationTree.Routes.DeliveryState.name) { parameters ->
            OrderDeliveryScreen(parameters as OrderStatesParameters)
        }
    }
}