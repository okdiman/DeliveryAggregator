package domain

import data.AuthRepository
import domain.usecase.GetAuthInfoUseCase

class GetAuthInfoUseCaseImpl(
    private val repository: AuthRepository
) : GetAuthInfoUseCase {
    override suspend fun invoke() {
        repository.getAuthInfo()
    }
}