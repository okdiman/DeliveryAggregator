package root.domain

import domain.AuthRepository
import domain.model.SignUpModel
import domain.usecase.SignUpUseCase

class SignUpUseCaseImpl(
    private val repository: AuthRepository
) : SignUpUseCase {
    override suspend fun invoke(model: SignUpModel) {
        repository.signUp(model)
    }
}