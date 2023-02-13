package notifications.domain.model

enum class RouteNotificationsStatus(val status: String) {
    NEW("new"),
    CANCELLED("cancelled"),
    CHANGED("changed"),
    DONE("done")
}