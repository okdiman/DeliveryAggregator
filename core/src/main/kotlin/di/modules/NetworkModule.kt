package di.modules

import devmenu.domain.DevMenuRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import network.interceptor.CurlLoggingInterceptor
import network.interceptor.ErrorInterceptor
import network.interceptor.HeadersInterceptor
import network.provider.OkHttpClientBuilderProvider
import network.provider.RetrofitProvider
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
internal fun networkModule() = module {
    single {
        @Suppress("JSON_FORMAT_REDUNDANT")
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }.asConverterFactory("application/json".toMediaType())
    }
    single {
        get<OkHttpClient.Builder>().build()
    }
    single {
        OkHttpClientBuilderProvider(
            get(),
            get(),
            get()
        ).provide()
    }
    single {
        RetrofitProvider(get(), get()).provide(get<DevMenuRepository>().getServerUrlSync())
    }
    factory { ErrorInterceptor() }
    factory { CurlLoggingInterceptor() }
    factory { HeadersInterceptor(get(), get()) }
}
