package notifications.domain.usecase

import android.text.Spanned
import androidx.core.text.toSpannable
import notifications.NotificationsConstant
import notifications.domain.model.RouteNotificationsStatus
import org.threeten.bp.format.DateTimeFormatter
import trinity_monsters.delivery_aggregator.core_ui.R
import utils.CommonConstants
import utils.ext.DateFormats
import utils.ext.toLocalZonedDateTime
import utils.resource.domain.ResourceInteractor
import utils.setBoldSpan
import utils.ext.toString

class RouteNotificationBodyMapper(
    private val resourceInteractor: ResourceInteractor
) {
    operator fun invoke(data: Map<String, String>): Spanned {
        val remoteStatus = data[NotificationsConstant.Route.STATUS].orEmpty()
        val routeId = buildString {
            append(CommonConstants.Helpers.NUMBER + data[NotificationsConstant.Route.ROUTE_ID].orEmpty())
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