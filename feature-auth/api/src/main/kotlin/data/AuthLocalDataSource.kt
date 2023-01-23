package data

interface AuthLocalDataSource {
    suspend fun saveAccessToken(token: String)
    suspend fun getAccessToken(): String?
    suspend fun savePushToken(token: String)
    fun getPushTokenSync(): String?
    fun getAccessTokenSync(): String?
    suspend fun clear()
}