package presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import domain.usecase.GetAuthInfoUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.viewmodel.model.SplashAction
import presentation.viewmodel.model.SplashEvent
import presentation.viewmodel.model.SplashState

class SplashViewModel : BaseViewModel<SplashState, SplashAction, SplashEvent>(
    initialState = SplashState()
), KoinComponent {
    private val getAuthInfo by inject<GetAuthInfoUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    init {
        checkAuthorization()
    }

    private fun checkAuthorization() {
        launchJob(context = appDispatchers.network, onError = {
            viewAction = SplashAction.OpenAuthorizationFlow
        }) {
            getAuthInfo()
            viewAction = SplashAction.OpenMainFlow
        }
    }
}