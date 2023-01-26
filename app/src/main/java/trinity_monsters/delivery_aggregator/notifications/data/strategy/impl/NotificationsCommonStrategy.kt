package trinity_monsters.delivery_aggregator.notifications.data.strategy.impl

import android.content.Context
import android.content.Intent
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsFactory
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsManager
import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel
import trinity_monsters.delivery_aggregator.notifications.data.strategy.NotificationsStrategy
import notifications.NotificationsConstant
import trinity_monsters.delivery_aggregator.root.presentation.MainActivity

class NotificationsCommonStrategy(
    private val context: Context,
    private val manager: NotificationsManager,
    private val factory: NotificationsFactory
) : NotificationsStrategy {
    override fun handle(model: NotificationsModel) {
        val channelId = NotificationsConstant.PushChannel.Info.CHANNEL_ID
        factory.create(getDefaultIntent(), model, channelId).apply {
            manager.show(this)
        }
    }

    private fun getDefaultIntent() = Intent(context, MainActivity::class.java)
}