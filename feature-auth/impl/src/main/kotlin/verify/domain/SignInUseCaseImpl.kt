package verify.domain

import domain.AuthRepository
import domain.model.AuthSignInModel
import domain.usecase.SignInUseCase

class SignInUseCaseImpl(
    private val repository: AuthRepository
) : SignInUseCase {
    override suspend fun invoke(model: AuthSignInModel) {
        repository.signIn(model)
    }
}