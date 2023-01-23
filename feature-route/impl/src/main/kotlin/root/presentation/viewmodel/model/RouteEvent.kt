package root.presentation.viewmodel.model

sealed interface RouteEvent {
    data class OnRouteClick(val id: Long, val index: Int) : RouteEvent
    object AcceptOrderClick : RouteEvent
    object OnNotificationsClick : RouteEvent
    object ResetAction : RouteEvent
    object OnRetryClick : RouteEvent
    object OnRefreshSwipe : RouteEvent
}