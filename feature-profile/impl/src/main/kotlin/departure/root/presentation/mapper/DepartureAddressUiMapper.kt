package departure.root.presentation.mapper

import departure.root.presentation.compose.model.DepartureAddressUiModel
import domain.model.AddressModel

class DepartureAddressUiMapper {
    fun map(models: List<AddressModel>) = models.map {
        DepartureAddressUiModel(
            address = it.street,
            isSelected = it.isSelected,
            id = it.id
        )
    }
}