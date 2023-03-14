package devmenu.domain

interface DevMenuRepository {
    suspend fun getServerUrl(): String
    fun getServerUrlSync(): String
    suspend fun setServerUrl(url: String)
    fun getAvailableHosts(): Map<String, String>
}