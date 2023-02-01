package root.domain

import domain.AuthRepository
import domain.usecase.IsAuthorizedUseCase

class IsAuthorizedUseCaseImpl(private val repository: AuthRepository) : IsAuthorizedUseCase {
    override suspend fun invoke() = repository.isAuthorized()
}