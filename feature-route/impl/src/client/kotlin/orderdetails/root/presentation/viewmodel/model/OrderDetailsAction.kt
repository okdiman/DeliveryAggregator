package orderdetails.root.presentation.viewmodel.model

import android.net.Uri

sealed interface OrderDetailsAction {
    object OpenPreviousScreen : OrderDetailsAction
    object OpenDeleteOrderScreen : OrderDetailsAction
    object OpenAdditionalInfo : OrderDetailsAction
    data class OpenPaymentInBrowser(val paymentUri: Uri) : OrderDetailsAction
    data class OpenImageViewerDialog(val uri: Uri) : OrderDetailsAction
}
