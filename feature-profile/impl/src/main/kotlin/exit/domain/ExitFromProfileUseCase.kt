package exit.domain

import domain.ProfileRepository

class ExitFromProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() {
        repository.exitFromProfile()
    }
}