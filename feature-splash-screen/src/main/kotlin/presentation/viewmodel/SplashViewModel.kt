package presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import domain.usecase.GetAuthInfoUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import network.state.domain.NetworkStateInteractor
import notifications.CreateNotificationChannelUseCase
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
    private val networkStateInteractor by inject<NetworkStateInteractor>()
    private val createNotificationChannel by inject<CreateNotificationChannelUseCase>()

    init {
        networkStateInteractor.isNetworkAvailableFlow()
            .onEach { isAvailable ->
                if (isAvailable) {
                    createNotificationChannel()
                    checkAuthorization()
                } else {
                    viewState = viewState.copy(isError = true)
                }
            }.launchIn(viewModelScope)
    }

    override fun obtainEvent(viewEvent: SplashEvent) {
        when (viewEvent) {
            SplashEvent.OnRetryClick -> checkAuthorization()
        }
    }

    private fun checkAuthorization() {
        if (networkStateInteractor.isInternetOn()) {
            launchJob(context = appDispatchers.network, onError = {
                viewAction = SplashAction.OpenAuthorizationFlow
            }) {
                getAuthInfo()
                viewAction = SplashAction.OpenMainFlow
            }
        }
    }
}