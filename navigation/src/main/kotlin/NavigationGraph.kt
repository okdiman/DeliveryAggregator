import flow.authFlow
import flow.mainFlow
import flow.registrationFlow
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.generateGraph() {
    authFlow()
    registrationFlow()
    mainFlow()
}