package departure.presentation

import departure.presentation.compose.model.DepartureAddressUiModel
import domain.model.AddressModel

class DepartureAddressUiMapper {
    fun map(models: List<AddressModel>) = models.map {
        DepartureAddressUiModel(
            address = it.street,
            comment = it.comment,
            isSelected = it.isSelected,
            id = it.id
        )
    }
}