package domain.usecase

import domain.model.VerifyCodeModel

interface GetVerifyCodeUseCase : suspend (VerifyCodeModel) -> Unit