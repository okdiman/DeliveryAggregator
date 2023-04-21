package notifications

object NotificationsConstant {
    const val PUSH_TYPE = "type"

    object Types {
        const val ROUTE = "route"
        const val REQUEST = "request"
        const val ASSIGNED_REQUEST = "assignedRequest"
    }

    object PushChannel {
        object Route {
            const val CHANNEL_ID = "route"
            const val CHANNEL_NAME = "Информация о заказе"
        }

        object Info {
            const val CHANNEL_ID = "info"
            const val CHANNEL_NAME = "Общая информация"
        }
    }

    object DataKeys {
        const val ROUTE_ID = "routeId"
        const val REQUEST_ID = "requestId"
        const val STATUS = "status"
        const val DATE = "date"
        const val DESTINATION = "destination"
    }
}