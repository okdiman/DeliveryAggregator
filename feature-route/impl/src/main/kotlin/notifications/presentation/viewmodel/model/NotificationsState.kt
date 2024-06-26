package notifications.presentation.viewmodel.model

import notifications.presentation.compose.model.NotificationUiModel

data class NotificationsState(
    val notifications: List<NotificationUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)