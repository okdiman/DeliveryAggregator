package domain.usecase

import domain.model.AuthSignUpModel

interface SignUpUseCase : suspend (AuthSignUpModel) -> Unit