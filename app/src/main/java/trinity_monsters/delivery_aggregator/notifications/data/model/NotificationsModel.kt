package trinity_monsters.delivery_aggregator.notifications.data.model

data class NotificationsModel(
    val data: Map<String, String>,
    val pushId: String,
    val title: String,
    val body: String
)