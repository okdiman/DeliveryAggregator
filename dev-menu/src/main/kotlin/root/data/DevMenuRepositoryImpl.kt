package root.data

import android.content.SharedPreferences
import coroutines.AppDispatchers
import devmenu.domain.DevMenuRepository
import kotlinx.coroutines.withContext

class DevMenuRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val appDispatchers: AppDispatchers
) : DevMenuRepository {
    override suspend fun getServerUrl(): String {
        return withContext(appDispatchers.storage) {
            sharedPreferences.getString(ACTIVE_URL, null) ?: PROD_URL
        }
    }

    override fun getServerUrlSync(): String {
        return sharedPreferences.getString(ACTIVE_URL, null) ?: PROD_URL
    }

    override suspend fun setServerUrl(url: String) {
        withContext(appDispatchers.storage) {
            sharedPreferences.edit().putString(ACTIVE_URL, url).apply()
        }
    }

    override fun getAvailableHosts() = AVAILABLE_HOSTS

    private companion object {
        const val ACTIVE_URL = "active_url"
        const val PROD_URL = "http://51.250.79.83:8080"
        private val AVAILABLE_HOSTS = mapOf(
            "Prod" to PROD_URL,
            "Test" to "http://test.server.ru",
        )
    }
}