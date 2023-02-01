package trinity_monsters.delivery_aggregator.notifications.data.strategy.impl

import android.content.Context
import android.content.Intent
import android.net.Uri
import deeplinks.DeeplinksConstants.DEEPLINK_URI_BASE
import deeplinks.DeeplinksConstants.DESTINATION_PARAM
import navigation.NavigationTree
import notifications.NotificationsConstant
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsFactory
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsManager
import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel
import trinity_monsters.delivery_aggregator.notifications.data.strategy.NotificationsStrategy
import trinity_monsters.delivery_aggregator.root.presentation.MainActivity

class NotificationsCommonStrategy(
    private val context: Context,
    private val manager: NotificationsManager,
    private val factory: NotificationsFactory
) : NotificationsStrategy {
    override fun handle(model: NotificationsModel) {
        val channelId = NotificationsConstant.PushChannel.Info.CHANNEL_ID
        val intent = Intent(context, MainActivity::class.java).apply {
            data = Uri.parse(buildString {
                append(DEEPLINK_URI_BASE + DESTINATION_PARAM + NavigationTree.Main.MainFlow.name)
            })
        }
        factory.create(intent, model, channelId).apply {
            manager.show(this)
        }
    }
}