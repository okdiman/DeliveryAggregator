package notifications.data.mapper

import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.core.text.toSpannable
import notifications.NotificationsConstant.Route.ROUTE_ID
import notifications.NotificationsConstant.Route.STATUS
import notifications.domain.model.RouteNotificationsStatus
import trinity_monsters.delivery_aggregator.core_ui.R
import utils.CommonConstants.Helpers.NUMBER
import utils.ext.setBoldSpan
import utils.getAnnotatedString
import utils.resource.domain.ResourceInteractor

class RouteNotificationBodyMapper(
    private val resourceInteractor: ResourceInteractor
) {
    operator fun invoke(data: Map<String, String>): Spanned {
        val remoteStatus = getStatus(data)
        val routeId = getRouteId(data).toSpannable()
            .apply {
                setBoldSpan()
            }
        val bodyBase = getBodyBase(remoteStatus)
        return resourceInteractor.getSpannedString(bodyBase, routeId)
    }

    fun mapToAnnotated(data: Map<String, String>): AnnotatedString {
        val remoteStatus = getStatus(data)
        val routeId = getRouteId(data)
        val bodyBase = getBodyBase(remoteStatus)
        val isKnownNotificationStatus = RouteNotificationsStatus.values().find { it.status == remoteStatus } != null
        val body = if (isKnownNotificationStatus) {
            String.format(
                resourceInteractor.getString(bodyBase),
                routeId
            )
        } else resourceInteractor.getString(bodyBase)
        return getAnnotatedString(body, listOf(routeId))
    }

    private fun getStatus(data: Map<String, String>) = data[STATUS].orEmpty()

    private fun getRouteId(data: Map<String, String>) = buildString {
        append(NUMBER + data[ROUTE_ID].orEmpty())
    }

    private fun getBodyBase(remoteStatus: String) =
        when (RouteNotificationsStatus.values().firstOrNull { it.status == remoteStatus }) {
            RouteNotificationsStatus.NEW -> R.string.notifications_new
            RouteNotificationsStatus.ASSIGNED -> R.string.notifications_contractor_assigned
            RouteNotificationsStatus.CANCELLED -> R.string.notifications_cancelled
            RouteNotificationsStatus.CHANGED -> R.string.notifications_changed
            RouteNotificationsStatus.DONE -> R.string.notifications_done
            else -> R.string.common_empty_error
        }
}
