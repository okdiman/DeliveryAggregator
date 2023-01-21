package login.domain

import domain.AuthRepository
import domain.model.AuthVerifyCodeModel
import domain.usecase.GetCodeUseCase

class GetCodeUseCaseImpl(
    private val repository: AuthRepository
) : GetCodeUseCase {
    override suspend fun invoke(model: AuthVerifyCodeModel) {
        repository.getVerifyCode(model)
    }
}