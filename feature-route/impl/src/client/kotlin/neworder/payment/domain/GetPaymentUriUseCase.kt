package neworder.payment.domain

import android.net.Uri

class GetPaymentUriUseCase {
    operator fun invoke(orderId: Long, token: String): Uri = Uri.parse(BASE_PAYMENT_URL)
        .buildUpon()
        .appendPath(orderId.toString())
        .appendPath(PAY_PATH)
        .appendQueryParameter(TOKEN_PARAM, token)
        .build()

    private companion object {
        private const val BASE_PAYMENT_URL = "https://delivery-aggregaor.site:4443/request/"
        private const val PAY_PATH = "pay"
        private const val TOKEN_PARAM = "token"
    }
}