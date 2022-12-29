package deleting.domain

import data.ProfileRepository

class DeleteProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() {
        repository.deleteProfile()
    }
}