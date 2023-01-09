package domain.usecase

import domain.model.AddressModel

interface GetUserAddressesUseCase : suspend () -> List<AddressModel>