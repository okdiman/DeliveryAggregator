package notifications.presentation.viewmodel.model

import android.net.Uri

sealed interface NotificationsAction {
    object OpenPreviousScreen : NotificationsAction
    data class OpenConfirmChangesScreen(val orderId: Long) : NotificationsAction
    data class OpenOrderDetails(val orderId: Long) : NotificationsAction
    data class OpenPaymentInBrowser(val paymentUri: Uri) : NotificationsAction
}
