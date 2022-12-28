package root.data

import data.ProfileRepository
import data.datasource.AuthLocalDataSource
import data.mapper.ProfileModelMapper
import domain.ProfileModel

class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val mapper: ProfileModelMapper,
    private val localDataSource: AuthLocalDataSource
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
        localDataSource.clear()
    }
}