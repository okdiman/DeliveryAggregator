package root.presentation.viewmodel

import BaseViewModel
import android.os.Process
import clipboard.domain.ClipboardInteractor
import kotlinx.coroutines.delay
import network.domain.GetPushTokenSyncUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.domain.DevMenuInteractor
import root.presentation.compose.model.HostUiModel
import root.presentation.viewmodel.model.DevMenuAction
import root.presentation.viewmodel.model.DevMenuEvent
import root.presentation.viewmodel.model.DevMenuState
import trinity_monsters.delivery_aggregator.dev_menu.R
import utils.resource.domain.ResourceInteractor

class DevMenuViewModel : BaseViewModel<DevMenuState, DevMenuAction, DevMenuEvent>(
    initialState = DevMenuState()
), KoinComponent {
    private val devMenuInteractor by inject<DevMenuInteractor>()
    private val getPushTokenSync by inject<GetPushTokenSyncUseCase>()
    private val resourceInteractor by inject<ResourceInteractor>()
    private val clipboardInteractor by inject<ClipboardInteractor>()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: DevMenuEvent) {
        when (viewEvent) {
            is DevMenuEvent.OnHostClick -> onHostClick(viewEvent.host)
            DevMenuEvent.OnTokenClick -> onTokenClick()
        }
    }

    private fun getContent() {
        launchJob {
            val activeHost = devMenuInteractor.getServerUrl()
            val pushToken = getPushTokenSync()
            viewState = DevMenuState(
                hosts = devMenuInteractor.getAvailableHosts().map {
                    HostUiModel(baseUrl = it.value, name = it.key, isActive = activeHost == it.value)
                },
                pushToken = pushToken.orEmpty()
            )
        }
    }

    private fun onHostClick(host: HostUiModel) {
        launchJob {
            val currentBaseUrl = devMenuInteractor.getServerUrl()
            if (currentBaseUrl != host.baseUrl) {
                devMenuInteractor.setServerUrl(host.baseUrl)
                viewState = viewState.copy(hosts = viewState.hosts.map {
                    it.isActive = host == it
                    it
                })
                viewAction = DevMenuAction.ShowToast(resourceInteractor.getString(R.string.dev_menu_restart_app))
                delay(TOAST_DELAY)
                Process.killProcess(Process.myPid())
            }
        }
    }

    private fun onTokenClick() {
        clipboardInteractor.setText(TOKEN, viewState.pushToken)
        viewAction = DevMenuAction.ShowToast(resourceInteractor.getString(R.string.dev_menu_token_copied))
    }

    private companion object {
        const val TOKEN = "token"
        const val TOAST_DELAY = 2000L
    }
}