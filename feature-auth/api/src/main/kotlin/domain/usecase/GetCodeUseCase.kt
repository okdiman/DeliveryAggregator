package domain.usecase

import domain.model.AuthVerifyCodeModel

interface GetCodeUseCase : suspend (AuthVerifyCodeModel) -> Unit