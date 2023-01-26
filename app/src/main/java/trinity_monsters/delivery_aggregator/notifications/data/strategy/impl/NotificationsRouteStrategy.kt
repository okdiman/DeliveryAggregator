package trinity_monsters.delivery_aggregator.notifications.data.strategy.impl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsFactory
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsManager
import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel
import trinity_monsters.delivery_aggregator.notifications.data.strategy.NotificationsStrategy
import notifications.NotificationsConstant
import notifications.NotificationsConstant.PUSH_BUNDLE
import notifications.NotificationsConstant.Route.ROUTE_ID
import notifications.NotificationsConstant.Route.STATUS
import trinity_monsters.delivery_aggregator.root.presentation.MainActivity

class NotificationsRouteStrategy(
    private val context: Context,
    private val manager: NotificationsManager,
    private val factory: NotificationsFactory
) : NotificationsStrategy {
    override fun handle(model: NotificationsModel) {
        val channelId = NotificationsConstant.PushChannel.Route.CHANNEL_ID
        val bundle = Bundle().apply {
            putString(ROUTE_ID, model.data[ROUTE_ID].orEmpty())
            putString(STATUS, model.data[STATUS].orEmpty())
        }
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(PUSH_BUNDLE, bundle)
        }
        factory.create(intent, model, channelId).apply {
            manager.show(this)
        }
    }
}