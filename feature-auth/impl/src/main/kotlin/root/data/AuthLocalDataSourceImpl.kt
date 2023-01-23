package root.data

import android.content.SharedPreferences
import coroutines.AppDispatchers
import data.AuthLocalDataSource
import kotlinx.coroutines.withContext

class AuthLocalDataSourceImpl(
    private val sharedPreferences: SharedPreferences,
    private val dispatchers: AppDispatchers
) : AuthLocalDataSource {

    override suspend fun saveAccessToken(token: String) {
        withContext(dispatchers.storage) {
            sharedPreferences.edit()
                .putString(USER_TOKEN, token)
                .apply()
        }
    }

    override suspend fun getAccessToken(): String? {
        return withContext(dispatchers.storage) {
            sharedPreferences.getString(USER_TOKEN, null)
        }
    }

    override suspend fun savePushToken(token: String) {
        withContext(dispatchers.storage) {
            sharedPreferences.edit()
                .putString(PUSH_TOKEN, token)
                .apply()
        }
    }

    override fun getPushTokenSync(): String? {
        return sharedPreferences.getString(PUSH_TOKEN, null)
    }

    override fun getAccessTokenSync(): String? {
        return sharedPreferences.getString(USER_TOKEN, null)
    }

    override suspend fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    private companion object {
        const val USER_TOKEN = "user_token"
        const val PUSH_TOKEN = "push_token"
    }
}