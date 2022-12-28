import flow.authFlow
import flow.mainFlow
import flow.registrationFlow
import navigation.NavigationTree
import presentation.compose.SplashScreen
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.generateGraph() {
    screen(NavigationTree.Splash.Splash.name) {
        SplashScreen()
    }
    authFlow()
    registrationFlow()
    mainFlow()
}