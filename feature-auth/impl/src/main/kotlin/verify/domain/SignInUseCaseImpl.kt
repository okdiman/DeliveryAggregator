package verify.domain

import domain.AuthRepository
import domain.model.SignInModel
import domain.usecase.SignInUseCase

class SignInUseCaseImpl(
    private val repository: AuthRepository
) : SignInUseCase {
    override suspend fun invoke(model: SignInModel) {
        repository.signIn(model)
    }
}