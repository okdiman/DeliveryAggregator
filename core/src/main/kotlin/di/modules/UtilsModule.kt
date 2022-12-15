package di.modules

import com.google.gson.Gson
import org.koin.dsl.module

fun utilsModule() = module {
    factory { Gson() }
}