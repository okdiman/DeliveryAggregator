package root.presentation.compose.model

import androidx.annotation.StringRes
import trinity_monsters.delivery_aggregator.feature_route.impl.R

enum class OrderStatusUiModel(@StringRes val text: Int) {
    CREATED(R.string.order_status_new),
    CHANGED(R.string.order_status_changed),
    ASSIGNED(R.string.order_status_assigned),
    DELIVERY(R.string.order_status_delivery),
    DONE(R.string.order_status_done)
}
