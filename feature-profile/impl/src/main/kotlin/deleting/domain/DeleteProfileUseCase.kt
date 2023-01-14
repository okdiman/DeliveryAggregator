package deleting.domain

import domain.ProfileRepository

class DeleteProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() {
        repository.deleteProfile()
    }
}