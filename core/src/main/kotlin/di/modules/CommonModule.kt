package di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import androidx.security.crypto.MasterKeys.AES256_GCM_SPEC
import coroutines.AppDispatchers
import coroutines.JvmAppDispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun commonModule() = module {
    factory<AppDispatchers> { JvmAppDispatchers() }
    single {
        provideEncryptedSharedPrefs(androidContext())
    }
}

private fun provideEncryptedSharedPrefs(context: Context): SharedPreferences {
    val masterKey = MasterKeys.getOrCreate(AES256_GCM_SPEC)
    return EncryptedSharedPreferences.create(
        SHARED_PREFS_NAME,
        masterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

private const val SHARED_PREFS_NAME = "delivery_aggregation_prefs"