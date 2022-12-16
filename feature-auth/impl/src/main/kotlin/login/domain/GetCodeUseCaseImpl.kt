package login.domain

import data.AuthRepository
import domain.model.VerifyCodeModel
import domain.usecase.GetCodeUseCase

class GetCodeUseCaseImpl(
    private val repository: AuthRepository
) : GetCodeUseCase {
    override suspend fun invoke(model: VerifyCodeModel) {
        repository.getVerifyCode(model)
    }
}