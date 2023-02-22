package root.presentation.compose.model

import androidx.annotation.StringRes
import trinity_monsters.delivery_aggregator.feature_route.impl.R

enum class OrderStatusCategoryUiModel(
    @StringRes val filterText: Int,
) {
    ACTIVE(R.string.order_filter_active),
    DONE(R.string.order_filter_done),
    PAID(R.string.order_filter_paid),
}
