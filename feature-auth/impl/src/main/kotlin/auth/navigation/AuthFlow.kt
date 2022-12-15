package auth.navigation

import auth.presentation.compose.screen.AuthScreen
import offer.presentation.OfferScreen
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import navigation.NavigationTree

fun RootComposeBuilder.authFlow() {
    flow(NavigationTree.Auth.AuthFlow.name) {
        screen(NavigationTree.Auth.Login.name) {
            AuthScreen()
        }
        screen(NavigationTree.Auth.Offer.name) {
            OfferScreen()
        }
    }
}