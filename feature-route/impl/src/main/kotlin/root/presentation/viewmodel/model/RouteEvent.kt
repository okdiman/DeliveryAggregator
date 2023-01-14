package root.presentation.viewmodel.model

sealed interface RouteEvent {
    data class OnRouteClick(val id: String) : RouteEvent
    data class AcceptOrderClick(val id: String) : RouteEvent
    object OnNotificationsClick : RouteEvent
    object ResetAction : RouteEvent
}