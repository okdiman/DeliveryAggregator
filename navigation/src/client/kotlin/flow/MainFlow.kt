package flow

import departure.presentation.compose.DepartureScreen
import editing.presentation.EditProfileParameters
import editing.presentation.compose.EditProfileScreen
import navigation.NavigationTree
import neworder.ordercreated.presentation.OrderCreatedParameters
import neworder.ordercreated.presentation.OrderCreatedScreen
import neworder.root.presentation.compose.NewOrderScreen
import neworder.storage.presentation.compose.NewOrderStorageParameters
import neworder.storage.presentation.compose.NewOrderStorageScreen
import notifications.presentation.compose.NotificationsScreen
import offer.presentation.compose.OfferScreen
import presentation.DeeplinkParameters
import root.presentation.compose.OrderRequestsScreen
import root.presentation.compose.ProfileScreen
import ru.alexgladkov.odyssey.compose.extensions.customNavigation
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import tabs.BottomConfiguration
import tabs.BottomNavigationTab
import trinity_monsters.delivery_aggregator.navigation.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Suppress("LongMethod")
fun RootComposeBuilder.mainFlow(deeplinkParameters: DeeplinkParameters?) {
    customNavigation(
        name = NavigationTree.Main.MainFlow.name,
        tabsNavModel = BottomConfiguration()
    ) {
        tab(
            BottomNavigationTab(
                title = R_core.string.navigation_order_requests,
                icon = R.drawable.route_ic
            )
        ) {
            screen(NavigationTree.Main.Orders.name) {
                OrderRequestsScreen()
            }
        }
        tab(
            BottomNavigationTab(
                title = R_core.string.navigation_new_order,
                icon = R_core.drawable.navigation_new_route
            )
        ) {}
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
    screen(NavigationTree.Profile.DepartureAddress.name) {
        DepartureScreen()
    }
    screen(NavigationTree.Routes.Notifications.name) {
        NotificationsScreen()
    }
    flow(NavigationTree.NewOrder.Creating.name) {
        screen(NavigationTree.NewOrder.Creating.name) {
            NewOrderScreen()
        }
        screen(NavigationTree.NewOrder.Storages.name) { parameters ->
            NewOrderStorageScreen(parameters as NewOrderStorageParameters)
        }
        screen(NavigationTree.NewOrder.OrderCreated.name){parameters->
            OrderCreatedScreen(parameters as OrderCreatedParameters)
        }
    }
}