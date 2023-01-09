package departure.domain

import domain.AddressRepository
import domain.model.AddressModel
import domain.usecase.UpdateUserAddressUseCase

class UpdateUserAddressUseCaseImpl(
    private val repository: AddressRepository
) : UpdateUserAddressUseCase {
    override suspend fun invoke(model: AddressModel) {
        repository.updateUserAddress(model)
    }
}