package data

import domain.ProfileModel

interface ProfileRepository {
    suspend fun getProfile(): ProfileModel
    suspend fun updateProfile()
    suspend fun deleteProfile()
}