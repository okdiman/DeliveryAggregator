package departure.root.domain

import domain.AddressRepository
import domain.model.AddressModel

class AddUserAddressUseCaseImpl(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(addressModel: AddressModel) {
        repository.addUserAddress(addressModel)
    }
}