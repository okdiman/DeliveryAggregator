package neworder.payment.domain

import android.net.Uri
import devmenu.domain.DevMenuRepository

class GetPaymentUriUseCase(private val devMenuRepository: DevMenuRepository) {
    operator fun invoke(orderId: Long, token: String): Uri = Uri.parse(devMenuRepository.getPaymentBaseUrl())
        .buildUpon()
        .appendPath(REQUEST_PATH)
        .appendPath(orderId.toString())
        .appendPath(PAY_PATH)
        .appendQueryParameter(TOKEN_PARAM, token)
        .build()

    private companion object {
        private const val PAY_PATH = "pay"
        private const val REQUEST_PATH = "request"
        private const val TOKEN_PARAM = "token"
    }
}