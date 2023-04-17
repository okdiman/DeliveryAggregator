package notifications

object NotificationsConstant {
    const val PUSH_TYPE = "type"

    object PushChannel {
        object Route {
            const val CHANNEL_ID = "route"
            const val CHANNEL_ID_ALTERNATIVE = "request"
            const val CHANNEL_NAME = "Информация о заказе"
        }

        object Info {
            const val CHANNEL_ID = "info"
            const val CHANNEL_NAME = "Общая информация"
        }
    }

    object DataKeys {
        const val ROUTE_ID = "route_id"
        const val REQUEST_ID = "requestId"
        const val STATUS = "status"
        const val DATE = "date"
        const val DESTINATION = "destination"
    }
}
