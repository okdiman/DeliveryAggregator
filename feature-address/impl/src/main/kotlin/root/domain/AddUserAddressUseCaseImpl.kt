package root.domain

import domain.AddressRepository
import domain.model.AddressModel
import domain.usecase.AddUserAddressUseCase

class AddUserAddressUseCaseImpl(
    private val repository: AddressRepository
) : AddUserAddressUseCase {
    override suspend fun invoke(addressModel: AddressModel) {
        repository.addUserAddress(addressModel)
    }
}