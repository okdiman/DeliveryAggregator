package notifications

object NotificationsConstant {
    const val PUSH_TYPE = "type"
    const val PUSH_BUNDLE = "push_bundle"

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

    object Route{
        const val ROUTE_ID = "route_id"
        const val STATUS = "status"
        const val DATE = "date"
    }
}