package root.presentation.viewmodel.model

sealed interface RouteAction {
    data class OpenRouteDetail(
        val id: Long,
        val index: Int,
        val isNeedToUpdateAfterBack: Boolean = false
    ) : RouteAction

    object OpenNotifications : RouteAction
}