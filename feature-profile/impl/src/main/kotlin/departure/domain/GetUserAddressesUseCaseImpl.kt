package departure.domain

import domain.AddressRepository
import domain.usecase.GetUserAddressesUseCase

class GetUserAddressesUseCaseImpl(
    private val repository: AddressRepository
) : GetUserAddressesUseCase {
    override suspend fun invoke() = repository.getUserAddresses()
}