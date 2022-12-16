package verify.domain

import data.AuthRepository
import domain.model.SignInModel
import domain.usecase.SignInUseCase

class SignInUseCaseImpl(
    private val repository: AuthRepository
) : SignInUseCase {
    override suspend fun invoke(model: SignInModel) {
        repository.signIn(model)
    }
}