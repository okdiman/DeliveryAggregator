package verify.presentation.compose

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import navigation.NavigationTree
import presentation.parameters.CompanyParameters
import presentation.parameters.model.RegistrationStartUserModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import verify.presentation.VerifyParameters
import verify.presentation.viewmodel.VerifyViewModel
import verify.presentation.viewmodel.model.VerifyAction
import verify.presentation.viewmodel.model.VerifyEvent

@Composable
fun VerifyScreen(parameters: VerifyParameters) {
    val rootController = LocalRootController.current
    ViewModel(factory = { VerifyViewModel(parameters) }) { viewModel ->
        val action = viewModel.viewActions().observeAsState()
        val state = viewModel.viewStates().observeAsState()
        VerifyView(viewState = state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            VerifyAction.OpenPreviousScreen -> {
                rootController.popBackStack()
                viewModel.obtainEvent(VerifyEvent.ResetAction)
            }
            VerifyAction.OpenMainFlow -> {
                rootController.findRootController().present(
                    screen = NavigationTree.Main.MainFlow.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
            }
            VerifyAction.OpenRegistrationFlow -> {
                rootController.findRootController().present(
                    screen = NavigationTree.Registration.RegistrationFlow.name,
                    params = CompanyParameters(
                        RegistrationStartUserModel(
                            code = state.value.code,
                            phone = parameters.phone
                        )
                    ),
                    launchFlag = LaunchFlag.SingleNewTask
                )
            }
            else -> {}
        }
    }
}