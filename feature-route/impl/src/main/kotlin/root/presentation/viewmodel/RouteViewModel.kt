package root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.domain.usecase.GetActiveRouteUseCase
import root.presentation.mapper.RouteUiMapper
import root.presentation.viewmodel.model.RouteAction
import root.presentation.viewmodel.model.RouteEvent
import root.presentation.viewmodel.model.RouteState

class RouteViewModel : BaseViewModel<RouteState, RouteAction, RouteEvent>(
    initialState = RouteState()
), KoinComponent {
    private val getActiveRoute by inject<GetActiveRouteUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val mapper by inject<RouteUiMapper>()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: RouteEvent) {
        when (viewEvent) {
            is RouteEvent.AcceptOrderClick -> onAcceptOrderClick(viewEvent.id)
            is RouteEvent.OnRouteClick -> onRouteClick(viewEvent.id)
            RouteEvent.OnNotificationsClick -> onNotificationsClick()
            RouteEvent.ResetAction -> onResetAction()
            RouteEvent.OnRetryClick -> getContent()
            RouteEvent.OnRefreshSwipe -> {
                viewState = viewState.copy(isRefreshing = true)
                getContent()
            }
        }
    }

    private fun onAcceptOrderClick(id: Long) {
        launchJob {
            // TODO
            viewAction = RouteAction.OpenRouteDetail(id)
        }
    }

    private fun getContent() {
        launchJob(
            context = appDispatchers.network,
            onError = {
                viewState = viewState.copy(isLoading = false, isError = true, isRefreshing = false)
            }
        ) {
            viewState = viewState.copy(isError = false, isLoading = true)
            val route = getActiveRoute()
            viewState = viewState.copy(
                orders = mapper.map(route.orders),
                isLoading = false,
                isRefreshing = false
            )
        }
    }

    private fun onRouteClick(id: Long) {
        viewAction = RouteAction.OpenRouteDetail(id)
    }

    private fun onNotificationsClick() {
        viewAction = RouteAction.OpenNotifications
    }
}