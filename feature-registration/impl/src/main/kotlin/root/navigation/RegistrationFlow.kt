package root.navigation

import navigation.NavigationTree
import organization.company.presentation.compose.CompanyScreen
import presentation.CompanyParameters
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.registrationFlow() {
    flow(NavigationTree.Registration.RegistrationFlow.name) {
        screen(NavigationTree.Registration.Company.name) { parameters ->
            CompanyScreen(parameters as CompanyParameters)
        }
        screen(NavigationTree.Registration.Bank.name) {

        }
        screen(NavigationTree.Registration.Transport.name) {

        }
        screen(NavigationTree.Registration.User.name) {

        }
    }
}