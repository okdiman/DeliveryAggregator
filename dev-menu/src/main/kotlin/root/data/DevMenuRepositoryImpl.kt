package root.data

import android.content.SharedPreferences
import coroutines.AppDispatchers
import devmenu.domain.DevMenuRepository
import kotlinx.coroutines.withContext
import trinity_monsters.delivery_aggregator.core.BuildConfig

class DevMenuRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val appDispatchers: AppDispatchers
) : DevMenuRepository {
    override fun getPaymentBaseUrl(): String {
        return if (BuildConfig.DEBUG && getServerUrlSync() == TEST_URL) {
            TEST_PAYMENT_URL
        } else PROD_URL
    }

    override suspend fun getServerUrl(): String {
        return withContext(appDispatchers.storage) {
            sharedPreferences.getString(ACTIVE_URL, null) ?: TEST_URL
        }
    }

    override fun getServerUrlSync(): String {
        return if (BuildConfig.DEBUG) {
            sharedPreferences.getString(ACTIVE_URL, null) ?: TEST_URL
        } else PROD_URL
    }

    override suspend fun setServerUrl(url: String) {
        withContext(appDispatchers.storage) {
            sharedPreferences.edit().putString(ACTIVE_URL, url).apply()
        }
    }

    override fun getAvailableHosts() = AVAILABLE_HOSTS

    private companion object {
        /**
         * тестовый стенд для оплаты отличается от обычного тестового стенда, продовый стенд для оплаты ==
         * продовому стенду
         */
        const val TEST_PAYMENT_URL = "https://delivery-aggregaor.site:4443"
        const val ACTIVE_URL = "active_url"
        const val PROD_URL = "https://delivery-aggregator.ru"
        const val TEST_URL = "http://51.250.79.83:8080"
        val AVAILABLE_HOSTS = mapOf(
            "Prod" to PROD_URL,
            "Test" to TEST_URL,
        )
    }
}