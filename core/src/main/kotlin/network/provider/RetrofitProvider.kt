package network.provider

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitProvider(
    private val okHttpClient: OkHttpClient,
    private val converterFactory: Converter.Factory
) {
    fun provide(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()
    }
}