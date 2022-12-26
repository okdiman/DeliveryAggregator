package root.data

import data.ProfileRepository
import data.mapper.ProfileModelMapper
import domain.ProfileModel

class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val mapper: ProfileModelMapper
) : ProfileRepository {
    override suspend fun getProfile(): ProfileModel {
        val response = api.getProfile()
        return mapper.mapFromDataToDomain(response.dto)
    }

    override suspend fun updateProfile() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProfile() {
        TODO("Not yet implemented")
    }
}