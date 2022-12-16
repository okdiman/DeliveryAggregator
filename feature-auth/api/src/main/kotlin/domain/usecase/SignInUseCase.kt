package domain.usecase

import domain.model.SignInModel

interface SignInUseCase : suspend (SignInModel) -> Unit