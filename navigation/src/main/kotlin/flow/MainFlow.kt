package flow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import editing.presentation.compose.EditProfileScreen
import navigation.NavigationTree
import offer.presentation.compose.OfferScreen
import presentation.EditProfileParameters
import presentation.TransportProfileParameters
import root.presentation.compose.ProfileScreen
import ru.alexgladkov.odyssey.compose.extensions.customNavigation
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import tabs.BottomConfiguration
import tabs.BottomNavigationTab
import transport.presentation.compose.TransportProfileScreen
import trinity_monsters.wildberries_delivery_aggregator.navigation.R

fun RootComposeBuilder.mainFlow() {
    customNavigation(
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
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Рейсы")
                }
            }
        }
        tab(
            BottomNavigationTab(
                title = R.string.profile,
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
}