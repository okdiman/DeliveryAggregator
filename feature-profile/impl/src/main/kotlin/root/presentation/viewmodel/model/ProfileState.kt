package root.presentation.viewmodel.model

import root.presentation.compose.model.ProfileItemType
import root.presentation.compose.model.ProfileItemUiModel
import trinity_monsters.delivery_aggregator.feature_profile.impl.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

data class ProfileState(
    val name: String = "",
    val phone: String = "",
    val organizationName: String = "",
    val isLoading: Boolean,
    val isError: Boolean = false,
    val email: String = "",
    val uiModels: List<ProfileItemUiModel> = listOf(
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
)