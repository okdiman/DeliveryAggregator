package domain.usecase

import domain.model.AddressModel

interface UpdateUserAddressUseCase : suspend (AddressModel) -> Unit