package root.domain

import domain.AuthRepository
import domain.model.AuthSignUpModel
import domain.usecase.SignUpUseCase

class SignUpUseCaseImpl(
    private val repository: AuthRepository
) : SignUpUseCase {
    override suspend fun invoke(model: AuthSignUpModel) {
        repository.signUp(model)
    }
}