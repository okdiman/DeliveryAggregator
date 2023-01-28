package notifications.root.domain.model

enum class RouteNotificationsStatus(val status: String) {
    ASSIGNED("assigned"),
    CANCELLED("cancelled"),
    CHANGED("changed"),
    DONE("done")
}