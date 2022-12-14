package di

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
        RetrofitProvider(get(), get()).provide()
    }
}