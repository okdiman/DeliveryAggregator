package auth.domain

import data.AuthRepository
import domain.model.VerifyCodeModel
import domain.usecase.GetVerifyCodeUseCase

class GetVerifyCodeUseCaseImpl(
    private val repository: AuthRepository
) : GetVerifyCodeUseCase {
    override suspend fun invoke(p1: VerifyCodeModel) {
        repository.getVerifyCode(p1)
    }
}