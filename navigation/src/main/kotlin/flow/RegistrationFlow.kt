package flow

import navigation.NavigationTree
import organization.bank.presentation.compose.BankScreen
import organization.company.presentation.compose.CompanyScreen
import organization.bank.presentation.BankParameters
import presentation.parameters.CompanyParameters
import transport.presentation.TransportParameters
import user.presentation.UserParameters
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import transport.presentation.compose.TransportScreen
import user.presentation.compose.UserScreen

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
        screen(NavigationTree.Registration.User.name) { parameters ->
            UserScreen(parameters as UserParameters)
        }
    }
}