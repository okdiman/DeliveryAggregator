package notifications.domain

enum class RouteNotificationsStatus(val status: String) {
    ASSIGNED("assigned"),
    CANCELLED("cancelled"),
    CHANGED("changed"),
    DONE("done")
}