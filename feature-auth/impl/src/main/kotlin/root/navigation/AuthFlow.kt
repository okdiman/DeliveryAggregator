package root.navigation

import login.presentation.compose.LoginScreen
import navigation.NavigationTree
import offer.presentation.compose.OfferScreen
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import verify.presentation.compose.VerifyScreen
import presentation.VerifyParameters

fun RootComposeBuilder.authFlow() {
    flow(NavigationTree.Auth.AuthFlow.name) {
        screen(NavigationTree.Auth.Login.name) {
            LoginScreen()
        }
        screen(NavigationTree.Auth.Offer.name) {
            OfferScreen()
        }
        screen(NavigationTree.Auth.Verify.name) { params ->
            VerifyScreen(parameters = params as VerifyParameters)
        }
    }
}