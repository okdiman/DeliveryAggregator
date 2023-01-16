package data

interface AuthLocalDataSource {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    fun getTokenSync(): String?
    suspend fun clear()
}