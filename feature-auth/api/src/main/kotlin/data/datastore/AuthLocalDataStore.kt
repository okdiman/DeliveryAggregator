package data.datastore

interface AuthLocalDataStore {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun clear()
}