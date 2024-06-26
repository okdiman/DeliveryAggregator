package di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import coroutines.AppDispatchers
import coroutines.JvmAppDispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal fun commonModule() = module {
    factory<AppDispatchers> { JvmAppDispatchers() }
    single {
        provideEncryptedSharedPrefs(androidContext())
    }
}

private fun provideEncryptedSharedPrefs(context: Context): SharedPreferences {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    return EncryptedSharedPreferences.create(
        context,
        SHARED_PREFS_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

private const val SHARED_PREFS_NAME = "delivery_aggregation_prefs"