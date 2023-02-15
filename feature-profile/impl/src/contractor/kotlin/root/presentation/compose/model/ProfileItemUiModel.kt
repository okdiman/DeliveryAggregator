package root.presentation.compose.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import trinity_monsters.delivery_aggregator.feature_profile.impl.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

class ProfileItemUiModel(
    val type: ProfileItemType,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    internal companion object {
        val defaultList = listOf(
            ProfileItemUiModel(
                icon = R_core.drawable.geolocation_ic,
                title = R.string.profile_depart_address,
                type = ProfileItemType.DepartureAddress
            ),
            ProfileItemUiModel(
                icon = R.drawable.profile_transport_ic,
                title = R.string.profile_transport,
                type = ProfileItemType.Transport
            ),
            ProfileItemUiModel(
                icon = R.drawable.profile_support_ic,
                title = R.string.profile_support,
                type = ProfileItemType.Support
            ),
            ProfileItemUiModel(
                icon = R.drawable.profile_notifications_ic,
                title = R.string.profile_notifications,
                type = ProfileItemType.Notifications
            ),
            ProfileItemUiModel(
                icon = R.drawable.profile_offer_ic,
                title = R.string.profile_offer,
                type = ProfileItemType.Offer
            ),
            ProfileItemUiModel(
                icon = R.drawable.profile_exit_ic,
                title = R.string.exit,
                type = ProfileItemType.Exit
            )
        )
    }
}