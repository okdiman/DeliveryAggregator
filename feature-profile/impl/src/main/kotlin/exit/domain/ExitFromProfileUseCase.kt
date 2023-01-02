package exit.domain

import data.ProfileRepository

class ExitFromProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() {
        repository.exitFromProfile()
    }
}