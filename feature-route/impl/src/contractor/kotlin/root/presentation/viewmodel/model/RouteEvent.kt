package root.presentation.viewmodel.model

import permissions.AppPermissionState

sealed interface RouteEvent {
    data class OnRouteClick(val id: Long, val index: Int) : RouteEvent
    data class OnPermissionStateChanged(val state: AppPermissionState) : RouteEvent
    object AcceptOrderClick : RouteEvent
    object OnNotificationsClick : RouteEvent
    object ResetAction : RouteEvent
    object ResetState : RouteEvent
    object OnRetryClick : RouteEvent
    object OnRefreshSwipe : RouteEvent
    object OnRationaleDismiss : RouteEvent
}