package domain.usecase

import domain.model.AddressModel

interface AddUserAddressUseCase : suspend (AddressModel) -> Unit