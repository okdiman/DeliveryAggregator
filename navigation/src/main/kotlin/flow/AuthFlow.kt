package flow

import PdfScreenParameters
import login.presentation.compose.LoginScreen
import navigation.NavigationTree
import pdf.presentation.compose.PdfScreen
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import verify.presentation.VerifyParameters
import verify.presentation.compose.VerifyScreen

fun RootComposeBuilder.authFlow() {
    flow(NavigationTree.Auth.AuthFlow.name) {
        screen(NavigationTree.Auth.Login.name) {
            LoginScreen()
        }
        screen(NavigationTree.Auth.Pdf.name) { parameters ->
            PdfScreen(parameters = parameters as PdfScreenParameters)
        }
        screen(NavigationTree.Auth.Verify.name) { parameters ->
            VerifyScreen(parameters = parameters as VerifyParameters)
        }
    }
}