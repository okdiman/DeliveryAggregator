package flow

import login.presentation.compose.LoginScreen
import navigation.NavigationTree
import offer.presentation.compose.OfferScreen
import presentation.VerifyParameters
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import verify.presentation.compose.VerifyScreen

fun RootComposeBuilder.authFlow() {
    flow(NavigationTree.Auth.AuthFlow.name) {
        screen(NavigationTree.Auth.Login.name) {
            LoginScreen()
        }
        screen(NavigationTree.Auth.Offer.name) {
            OfferScreen()
        }
        screen(NavigationTree.Auth.Verify.name) { parameters ->
            VerifyScreen(parameters = parameters as VerifyParameters)
        }
    }
}