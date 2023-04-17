package notifications.domain.model

enum class RouteNotificationsStatus(val status: String) {
    NEW("new"),
    CANCELED("canceled"),
    CHANGED("changed"),
    ASSIGNED("assigned"),
    DELIVERY("delivery"),
    DONE("done"),
}
