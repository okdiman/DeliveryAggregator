package network.interceptor

import java.io.IOException
import network.domain.GetAuthTokenSyncUseCase
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor(
    private val getAuthToken: GetAuthTokenSyncUseCase
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().apply {
                val authHeader = getAuthToken()
                header(AUTHORIZATION, authHeader)
            }.build()
        )
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}