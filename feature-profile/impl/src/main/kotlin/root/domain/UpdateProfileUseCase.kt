package root.domain

import data.ProfileRepository
import domain.ProfileModel

class UpdateProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(model: ProfileModel) {
        repository.updateProfile(model)
    }
}