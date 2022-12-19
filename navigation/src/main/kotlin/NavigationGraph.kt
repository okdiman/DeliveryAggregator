import root.navigation.authFlow
import root.navigation.registrationFlow
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.generateGraph() {
    authFlow()
    registrationFlow()
}