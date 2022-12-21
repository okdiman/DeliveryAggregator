package domain.usecase

import domain.model.SignUpModel

interface SignUpUseCase : suspend (SignUpModel) -> Unit