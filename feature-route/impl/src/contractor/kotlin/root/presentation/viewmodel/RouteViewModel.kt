package root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import navigation.NavigationTree
import network.exceptions.NotFoundException
import notifications.domain.usecase.GetUnreadNotificationsCountUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import permissions.AppPermissionState
import permissions.PermissionsConstants.Notification
import permissions.domain.interactor.PermissionsInteractor
import presentation.DeeplinkParameters
import root.DeeplinkNavigatorHandler
import root.domain.usecase.AcceptRouteUseCase
import root.domain.usecase.GetActiveRouteUseCase
import root.presentation.mapper.RouteButtonUiModelMapper
import root.presentation.mapper.RouteUiMapper
import root.presentation.viewmodel.model.RouteAction
import root.presentation.viewmodel.model.RouteEvent
import root.presentation.viewmodel.model.RouteState

class RouteViewModel(private val deeplinkParameters: DeeplinkParameters?) :
    BaseViewModel<RouteState, RouteAction, RouteEvent>(initialState = RouteState()), KoinComponent {
    private val getActiveRoute by inject<GetActiveRouteUseCase>()
    private val getUnreadNotificationsCount by inject<GetUnreadNotificationsCountUseCase>()
    private val acceptRoute by inject<AcceptRouteUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val mapper by inject<RouteUiMapper>()
    private val buttonUiMapper by inject<RouteButtonUiModelMapper>()
    private val permission by inject<PermissionsInteractor>()
    private val deeplinkNavigatorHandler by inject<DeeplinkNavigatorHandler>()

    init {
        checkDeeplink()
        getContent()
        getUnreadNotificationCount()
    }

    override fun obtainEvent(viewEvent: RouteEvent) {
        when (viewEvent) {
            is RouteEvent.OnRouteClick -> onRouteClick(viewEvent.id, viewEvent.index)
            is RouteEvent.OnPermissionStateChanged -> onNotificationPermissionStateChanged(viewEvent.state)
            RouteEvent.AcceptOrderClick -> onAcceptOrderClick()
            RouteEvent.OnNotificationsClick -> onNotificationsClick()
            RouteEvent.ResetAction -> onResetAction()
            RouteEvent.OnRetryClick -> getContent()
            RouteEvent.OnRationaleDismiss -> onRationaleDismiss()
            RouteEvent.OnRefreshSwipe -> {
                viewState = viewState.copy(isRefreshing = true)
                getContent()
            }
        }
    }

    private fun onAcceptOrderClick() {
        launchJob {
            acceptRoute(viewState.id)
            viewAction = RouteAction.OpenRouteDetail(viewState.orders.first().id, 0, true)
        }
    }

    private fun getContent() {
        launchJob(
            context = appDispatchers.network,
            onError = {
                viewState = if (it is NotFoundException) {
                    viewState.copy(
                        isLoading = false,
                        isError = false,
                        isRefreshing = false,
                        orders = emptyList()
                    )
                } else {
                    viewState.copy(isLoading = false, isError = true, isRefreshing = false)
                }
            }
        ) {
            viewState = viewState.copy(isError = false, isLoading = true)
            val route = getActiveRoute()
            viewState = viewState.copy(
                id = route.id,
                orders = mapper.map(route.orders),
                buttonUiModel = buttonUiMapper.map(route.price, route.distance),
                status = route.status,
                isLoading = false,
                isRefreshing = false
            )
        }
    }

    private fun onNotificationPermissionStateChanged(state: AppPermissionState) {
        launchJob {
            when (state) {
                AppPermissionState.Rationale -> {
                    if (!permission.isShowRationaleDismissed(Notification)) {
                        viewState = viewState.copy(notificationsPermission = state)
                    }
                }
                else -> {
                    viewState = viewState.copy(notificationsPermission = state)
                }
            }
        }
    }

    private fun onRationaleDismiss() {
        launchJob {
            viewState = viewState.copy(notificationsPermission = AppPermissionState.Denied)
            permission.setRationaleDismissed(Notification)
        }
    }

    private fun onRouteClick(id: Long, index: Int) {
        viewAction = RouteAction.OpenRouteDetail(id, index)
    }

    private fun onNotificationsClick() {
        viewAction = RouteAction.OpenNotifications
    }

    private fun getUnreadNotificationCount() {
        launchJob(appDispatchers.network) {
            val count = getUnreadNotificationsCount()
            viewState = viewState.copy(
                notificationsCount = count
            )
        }
    }

    /**
     * навигация по диплинкам
     */
    private fun checkDeeplink() {
        val deeplinkUri = deeplinkParameters?.uri ?: return
        if (deeplinkNavigatorHandler.isAlreadyHandled(deeplinkUri)) return

        viewAction = when (deeplinkNavigatorHandler.getDestination(deeplinkUri)) {
            NavigationTree.Routes.Notifications.name -> RouteAction.OpenNotifications
            else -> null
        }
    }
}