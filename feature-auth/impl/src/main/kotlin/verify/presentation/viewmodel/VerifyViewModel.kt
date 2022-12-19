package verify.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import domain.model.SignInModel
import domain.usecase.SignInUseCase
import network.exceptions.ForbiddenException
import network.exceptions.UnauthorizedException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.VerifyParameters
import verify.domain.GetVerifyTitleUseCase
import verify.presentation.viewmodel.model.VerifyAction
import verify.presentation.viewmodel.model.VerifyEvent
import verify.presentation.viewmodel.model.VerifyState

class VerifyViewModel(
    private val parameters: VerifyParameters
) : BaseViewModel<VerifyState, VerifyAction, VerifyEvent>(
    initialState = VerifyState()
), KoinComponent {
    private val getVerifyTitle by inject<GetVerifyTitleUseCase>()
    private val signIn by inject<SignInUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    init {
        viewState = VerifyState(title = getVerifyTitle(parameters.phone))
    }

    override fun obtainEvent(viewEvent: VerifyEvent) {
        when (viewEvent) {
            is VerifyEvent.OnCodeChanged -> onCodeChanged(viewEvent.code)
            is VerifyEvent.OnBackClick -> onBackClick()
            is VerifyEvent.OnRetryCallClick -> onRetryCallClick()
            is VerifyEvent.TickerFinished -> onTickerFinished()
            is VerifyEvent.ResetAction -> viewAction = null
        }
    }

    private fun onTickerFinished() {
        viewState =
            viewState.copy(isRetryButtonAvailable = true, isTimerVisible = false)
    }

    private fun onRetryCallClick() {
        viewState =
            viewState.copy(isRetryButtonAvailable = false, isTimerVisible = true)
    }

    private fun onBackClick() {
        viewAction = VerifyAction.OpenPreviousScreen
    }

    private fun onCodeChanged(code: String) {
        val isCodeFilled = code.length == FULL_CODE
        viewState = viewState.copy(code = code, isLoading = isCodeFilled, isCodeError = false)
        if (isCodeFilled) {
            launchJob(context = appDispatchers.network, onError = {
                when (it) {
                    is UnauthorizedException -> {
                        viewAction = VerifyAction.OpenRegistrationFlow
                    }
                    is ForbiddenException -> {
                        viewState = viewState.copy(isCodeError = true)
                    }
                }
            }, onFinally = {
                viewState = viewState.copy(isLoading = false)
            }) {
                signIn(SignInModel(code.toInt(), parameters.phone))
                viewAction = VerifyAction.OpenMainFlow
            }
        }
    }

    companion object {
        private const val FULL_CODE = 4
    }
}