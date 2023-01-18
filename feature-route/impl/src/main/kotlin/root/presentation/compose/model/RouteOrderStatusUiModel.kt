package root.presentation.compose.model

import androidx.annotation.StringRes
import trinity_monsters.wildberries_delivery_aggregator.feature_route.impl.R

enum class RouteOrderStatusUiModel(@StringRes val text: Int) {
    ACTIVE(R.string.active_status),
    INPROGRESS(R.string.inprogress_status),
    DONE(R.string.done_status)
}