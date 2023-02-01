package notifications.root.data.mapper

import android.text.Spanned
import androidx.core.text.toSpannable
import notifications.NotificationsConstant
import notifications.NotificationsConstant.Route.ROUTE_ID
import notifications.NotificationsConstant.Route.STATUS
import notifications.root.domain.model.RouteNotificationsStatus
import org.threeten.bp.format.DateTimeFormatter
import trinity_monsters.delivery_aggregator.core_ui.R
import utils.CommonConstants.Helpers.NUMBER
import utils.ext.DateFormats
import utils.ext.toLocalZonedDateTime
import utils.ext.toString
import utils.resource.domain.ResourceInteractor
import utils.setBoldSpan

class RouteNotificationBodyMapper(
    private val resourceInteractor: ResourceInteractor
) {
    operator fun invoke(data: Map<String, String>): Spanned {
        val remoteStatus = data[STATUS].orEmpty()
        val routeId = buildString {
            append(NUMBER + data[ROUTE_ID].orEmpty())
        }.toSpannable()
            .apply {
                setBoldSpan()
            }
        val bodyBase =
            when (RouteNotificationsStatus.values().firstOrNull { it.status == remoteStatus }) {
                RouteNotificationsStatus.ASSIGNED -> R.string.notifications_info
                RouteNotificationsStatus.CANCELLED -> R.string.notifications_cancelled
                RouteNotificationsStatus.CHANGED -> R.string.notifications_changed
                RouteNotificationsStatus.DONE -> R.string.notifications_done
                else -> R.string.common_empty_error
            }
        return if (RouteNotificationsStatus.values()
                .firstOrNull { it.status == remoteStatus } == RouteNotificationsStatus.ASSIGNED
        ) {
            val date = data[NotificationsConstant.Route.DATE]
                .orEmpty()
                .toLocalZonedDateTime(DateTimeFormatter.ISO_DATE_TIME)
                .toString(DateFormats.FULL_DISPLAYED_DATE_TIME_FORMATTER)
                .toSpannable()
                .apply {
                    setBoldSpan()
                }
            resourceInteractor.getSpannedString(bodyBase, routeId, date)
        } else {
            resourceInteractor.getSpannedString(bodyBase, routeId)
        }
    }
}