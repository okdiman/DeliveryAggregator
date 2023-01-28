package root.data

import android.content.SharedPreferences
import domain.ProfileRepository
import domain.model.ProfileModel
import domain.usecase.notifications.GetNewFCMTokenUseCase
import root.data.mapper.ProfileModelMapper

class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val mapper: ProfileModelMapper,
    private val sharedPreferences: SharedPreferences,
    private val getNewFCMToken: GetNewFCMTokenUseCase
) : ProfileRepository {
    override suspend fun getProfile(): ProfileModel {
        val response = api.getProfile()
        return mapper.mapFromDataToDomain(response.dto)
    }

    override suspend fun updateProfile(model: ProfileModel) {
        val request = mapper.mapFromDomainToData(model)
        api.updateProfile(request)
    }

    override suspend fun deleteProfile() {
        api.deleteProfile()
        exitFromProfile()
    }

    override suspend fun exitFromProfile() {
        clearSharedPrefs()
        getNewFCMToken()
    }

    private fun clearSharedPrefs() {
        sharedPreferences.edit().clear().apply()
    }
}