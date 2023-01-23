package root.data

import domain.ProfileRepository
import data.AuthLocalDataSource
import root.data.mapper.ProfileModelMapper
import domain.model.ProfileModel
import domain.usecase.notifications.GetNewFCMTokenUseCase

class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val mapper: ProfileModelMapper,
    private val localDataSource: AuthLocalDataSource,
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
        localDataSource.clear()
        getNewFCMToken()
    }
}