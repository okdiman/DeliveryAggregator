package root.domain

import domain.ProfileRepository
import domain.model.ProfileModel

class UpdateProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(model: ProfileModel) {
        repository.updateProfile(model)
    }
}