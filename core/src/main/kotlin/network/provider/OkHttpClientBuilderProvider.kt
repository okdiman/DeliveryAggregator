package network.provider

import java.util.concurrent.TimeUnit
import network.interceptor.CurlLoggingInterceptor
import network.interceptor.ErrorInterceptor
import network.interceptor.HeadersInterceptor
import okhttp3.OkHttpClient
import trinity_monsters.delivery_aggregator.core.BuildConfig

class OkHttpClientBuilderProvider(
    private val errorInterceptor: ErrorInterceptor,
    private val curlInterceptor: CurlLoggingInterceptor,
    private val headersInterceptor: HeadersInterceptor
) {

    fun provide(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            readTimeout(ALL_TIMEOUTS_CONNECTION, TimeUnit.SECONDS)
            connectTimeout(ALL_TIMEOUTS_CONNECTION, TimeUnit.SECONDS)
            writeTimeout(ALL_TIMEOUTS_CONNECTION, TimeUnit.SECONDS)
            addInterceptor(headersInterceptor)
            addInterceptor(errorInterceptor)
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(curlInterceptor)
            }
        }
    }

    companion object {
        private const val ALL_TIMEOUTS_CONNECTION = 30L
    }
}