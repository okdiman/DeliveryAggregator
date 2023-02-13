package notifications.data.mapper

import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.core.text.toSpannable
import notifications.NotificationsConstant
import notifications.NotificationsConstant.Route.ROUTE_ID
import notifications.NotificationsConstant.Route.STATUS
import notifications.domain.model.RouteNotificationsStatus
import org.threeten.bp.format.DateTimeFormatter
import trinity_monsters.delivery_aggregator.core_ui.R
import utils.CommonConstants.Helpers.NUMBER
import utils.ext.DateFormats
import utils.ext.toLocalZonedDateTime
import utils.ext.toString
import utils.getAnnotatedString
import utils.resource.domain.ResourceInteractor
import utils.setBoldSpan

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
        return if (RouteNotificationsStatus.values()
                .firstOrNull { it.status == remoteStatus } == RouteNotificationsStatus.NEW
        ) {
            val date = getDate(data)
                .toSpannable()
                .apply {
                    setBoldSpan()
                }
            resourceInteractor.getSpannedString(bodyBase, routeId, date)
        } else {
            resourceInteractor.getSpannedString(bodyBase, routeId)
        }
    }

    fun mapToAnnotated(data: Map<String, String>): AnnotatedString {
        val remoteStatus = getStatus(data)
        val routeId = getRouteId(data)
        val date = getDate(data)
        val bodyBase = getBodyBase(remoteStatus)
        val body =
            when (RouteNotificationsStatus.values().firstOrNull { it.status == remoteStatus }) {
                RouteNotificationsStatus.NEW -> {
                    String.format(
                        resourceInteractor.getString(bodyBase),
                        routeId,
                        date
                    )
                }
                RouteNotificationsStatus.CANCELLED,
                RouteNotificationsStatus.CHANGED,
                RouteNotificationsStatus.DONE -> {
                    String.format(
                        resourceInteractor.getString(bodyBase),
                        routeId
                    )
                }
                else -> resourceInteractor.getString(bodyBase)
            }
        return if (RouteNotificationsStatus.values().firstOrNull {
                it.status == remoteStatus
            } == RouteNotificationsStatus.NEW
        ) {
            getAnnotatedString(body, listOf(routeId, date))
        } else {
            getAnnotatedString(body, listOf(routeId))
        }
    }

    private fun getStatus(data: Map<String, String>) = data[STATUS].orEmpty()

    private fun getRouteId(data: Map<String, String>) = buildString {
        append(NUMBER + data[ROUTE_ID].orEmpty())
    }

    private fun getDate(data: Map<String, String>) = data[NotificationsConstant.Route.DATE]
        .orEmpty()
        .toLocalZonedDateTime(DateTimeFormatter.ISO_DATE_TIME)
        .toString(DateFormats.FULL_DISPLAYED_DATE_TIME_FORMATTER)

    private fun getBodyBase(remoteStatus: String) =
        when (RouteNotificationsStatus.values().firstOrNull { it.status == remoteStatus }) {
            RouteNotificationsStatus.NEW -> R.string.notifications_info
            RouteNotificationsStatus.CANCELLED -> R.string.notifications_cancelled
            RouteNotificationsStatus.CHANGED -> R.string.notifications_changed
            RouteNotificationsStatus.DONE -> R.string.notifications_done
            else -> R.string.common_empty_error
        }
}