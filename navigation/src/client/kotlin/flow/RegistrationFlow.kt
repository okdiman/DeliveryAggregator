package flow

import navigation.NavigationTree
import organization.bank.presentation.BankParameters
import bank.presentation.BankScreen
import company.presentation.CompanyScreen
import presentation.parameters.CompanyParameters
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import user.presentation.UserParameters
import user.presentation.compose.UserScreen

fun RootComposeBuilder.registrationFlow() {
    flow(NavigationTree.Registration.RegistrationFlow.name) {
        screen(NavigationTree.Registration.Company.name) { parameters ->
            CompanyScreen(parameters as CompanyParameters)
        }
        screen(NavigationTree.Registration.Bank.name) { parameters ->
            BankScreen(parameters as BankParameters)
        }
        screen(NavigationTree.Registration.User.name) { parameters ->
            UserScreen(parameters as UserParameters)
        }
    }
}