package devmenu.domain

interface DevMenuRepository {
    fun getPaymentBaseUrl(): String
    suspend fun getServerUrl(): String
    fun getServerUrlSync(): String
    suspend fun setServerUrl(url: String)
    fun getAvailableHosts(): Map<String, String>
}