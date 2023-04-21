package notifications.domain.model

enum class RouteNotificationsStatus(val status: String) {
    NEW("new"),
    ACCEPTED("accepted"),
    CANCELED("canceled"),
    CHANGED("changed"),
    ASSIGNED("assigned"),
    DELIVERY("delivery"),
    DONE("done"),
}