package domain

import domain.model.ProfileModel

interface ProfileRepository {
    suspend fun getProfile(): ProfileModel
    suspend fun updateProfile(model: ProfileModel)
    suspend fun deleteProfile()
    suspend fun exitFromProfile()
}