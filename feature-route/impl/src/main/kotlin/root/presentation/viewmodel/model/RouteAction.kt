package root.presentation.viewmodel.model

sealed interface RouteAction {
    data class OpenRouteDetail(val id: String) : RouteAction
    object OpenNotifications : RouteAction
}