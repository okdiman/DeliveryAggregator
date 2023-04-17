package trinity_monsters.delivery_aggregator.notifications.data.strategy.impl

import android.content.Context
import android.content.Intent
import android.net.Uri
import deeplinks.DeeplinksConstants.DEEPLINK_URI_BASE
import deeplinks.DeeplinksConstants.DESTINATION_PARAM
import deeplinks.DeeplinksConstants.ROUTE_ID_PARAM
import navigation.NavigationTree
import notifications.NotificationsConstant
import notifications.NotificationsConstant.DataKeys.STATUS
import notifications.domain.model.RouteNotificationsStatus
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsFactory
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsManager
import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel
import trinity_monsters.delivery_aggregator.notifications.data.strategy.NotificationsStrategy
import trinity_monsters.delivery_aggregator.root.presentation.MainActivity

class NotificationsRouteStrategy(
    private val context: Context,
    private val manager: NotificationsManager,
    private val factory: NotificationsFactory
) : NotificationsStrategy {
    override fun handle(model: NotificationsModel) {
        val channelId = NotificationsConstant.PushChannel.Route.CHANNEL_ID
        val status = RouteNotificationsStatus.values()
            .firstOrNull { it.status == model.data[STATUS] }
        val destination = when (status) {
            RouteNotificationsStatus.CANCELLED, RouteNotificationsStatus.DONE -> {
                NavigationTree.Routes.Notifications.name
            }
            else -> NavigationTree.Main.MainFlow.name
        }
        val routeId = model.id()?.toString().orEmpty()
        val intent = Intent(context, MainActivity::class.java).apply {
            data = Uri.parse(buildString {
                append(DEEPLINK_URI_BASE + DESTINATION_PARAM + destination + ROUTE_ID_PARAM + routeId)
            })
        }
        factory.create(intent, model, channelId).apply {
            manager.show(this)
        }
    }
}
