package root.presentation.compose.model

import androidx.annotation.StringRes
import trinity_monsters.wildberries_delivery_aggregator.feature_route.impl.R

enum class RouteOrderStatusUiModel(@StringRes val text: Int) {
    ACTIVE(R.string.route_active_status),
    IN_PROGRESS(R.string.route_inprogress_status),
    DONE(R.string.route_done_status)
}