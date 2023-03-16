package orderdetails.root.presentation.viewmodel.model

import android.net.Uri

sealed interface OrderDetailsAction {
    object OpenPreviousScreen : OrderDetailsAction
    object OpenAdditionalInfo : OrderDetailsAction
    data class OpenPaymentInBrowserAndReturn(val paymentUri: Uri) : OrderDetailsAction
}
