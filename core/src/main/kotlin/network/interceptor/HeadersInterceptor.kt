package network.interceptor

import java.io.IOException
import network.domain.GetAuthTokenSyncUseCase
import network.domain.GetPushTokenSyncUseCase
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor(
    private val getAuthToken: GetAuthTokenSyncUseCase,
    private val getPushToken: GetPushTokenSyncUseCase
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().apply {
                getAuthToken()?.let { authHeader ->
                    header(AUTHORIZATION, authHeader)
                }
                getPushToken()?.let { pushHeader ->
                    header(PUSH, pushHeader)
                }
            }.build()
        )
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val PUSH = "Messaging"
    }
}