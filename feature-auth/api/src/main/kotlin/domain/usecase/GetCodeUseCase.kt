package domain.usecase

import domain.model.VerifyCodeModel

interface GetCodeUseCase : suspend (VerifyCodeModel) -> Unit