package root.domain

import data.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke() = repository.getProfile()
}