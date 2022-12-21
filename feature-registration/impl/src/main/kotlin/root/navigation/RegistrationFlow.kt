package root.navigation

import navigation.NavigationTree
import organization.bank.presentation.compose.BankScreen
import organization.company.presentation.compose.CompanyScreen
import presentation.parameters.BankParameters
import presentation.parameters.CompanyParameters
import presentation.parameters.TransportParameters
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import transport.presentation.compose.TransportScreen

fun RootComposeBuilder.registrationFlow() {
    flow(NavigationTree.Registration.RegistrationFlow.name) {
        screen(NavigationTree.Registration.Company.name) { parameters ->
            CompanyScreen(parameters as CompanyParameters)
        }
        screen(NavigationTree.Registration.Bank.name) { parameters ->
            BankScreen(parameters as BankParameters)
        }
        screen(NavigationTree.Registration.Transport.name) { parameters ->
            TransportScreen(parameters as TransportParameters)
        }
        screen(NavigationTree.Registration.User.name) {

        }
    }
}