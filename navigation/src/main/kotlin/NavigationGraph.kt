import flow.authFlow
import flow.mainFlow
import flow.registrationFlow
import navigation.NavigationTree
import presentation.DeeplinkParameters
import presentation.compose.SplashScreen
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.generateGraph(deeplinkParameters: DeeplinkParameters? = null) {
    screen(NavigationTree.Splash.Splash.name) {
        SplashScreen(deeplinkParameters)
    }
    authFlow()
    registrationFlow()
    mainFlow(deeplinkParameters)
}