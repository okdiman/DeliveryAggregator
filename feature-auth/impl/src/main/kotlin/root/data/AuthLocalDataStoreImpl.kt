package root.data

import android.content.SharedPreferences
import coroutines.AppDispatchers
import data.datastore.AuthLocalDataStore
import kotlinx.coroutines.withContext

class AuthLocalDataStoreImpl(
    private val sharedPreferences: SharedPreferences,
    private val dispatchers: AppDispatchers
) : AuthLocalDataStore {

    override suspend fun saveToken(token: String) {
        withContext(dispatchers.storage) {
            sharedPreferences.edit()
                .putString(USER_TOKEN, token)
                .apply()
        }
    }

    override suspend fun getToken(): String? {
        return sharedPreferences.getString(USER_TOKEN, null)
    }

    override suspend fun clear() {
        sharedPreferences.edit().clear()
    }

    private companion object {
        const val USER_TOKEN = "user_token"
    }
}