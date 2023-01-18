package root.presentation.viewmodel.model

sealed interface RouteAction {
    data class OpenRouteDetail(val id: Long) : RouteAction
    object OpenNotifications : RouteAction
}