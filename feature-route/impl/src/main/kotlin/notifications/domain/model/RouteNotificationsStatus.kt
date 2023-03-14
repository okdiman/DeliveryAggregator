package notifications.domain.model

enum class RouteNotificationsStatus(val status: String) {
    NEW("new"),
    CANCELLED("cancelled"),
    CHANGED("changed"),
    ASSIGNED("assigned"),
    DELIVERY("delivery"),
    DONE("done"),
}
