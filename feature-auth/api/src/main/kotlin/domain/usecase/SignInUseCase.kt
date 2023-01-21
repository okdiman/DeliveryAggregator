package domain.usecase

import domain.model.AuthSignInModel

interface SignInUseCase : suspend (AuthSignInModel) -> Unit