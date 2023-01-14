package root.domain

import domain.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke() = repository.getProfile()
}