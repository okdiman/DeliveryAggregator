package di.modules

import network.interceptor.CurlLoggingInterceptor
import network.interceptor.ErrorInterceptor
import network.provider.OkHttpClientBuilderProvider
import network.provider.RetrofitProvider
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

fun networkModule() = module {
    single<Converter.Factory> {
        GsonConverterFactory.create()
    }
    single {
        get<OkHttpClient.Builder>().build()
    }
    single {
        OkHttpClientBuilderProvider(
            get(),
            get()
        ).provide()
    }
    single {
        RetrofitProvider(get(), get()).provide()
    }
    factory { ErrorInterceptor() }
    factory { CurlLoggingInterceptor() }
}