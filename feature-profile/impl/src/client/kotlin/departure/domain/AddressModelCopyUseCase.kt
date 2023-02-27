package departure.domain

import domain.model.AddressModel
import presentation.AddressSuggestUiModel

class AddressModelCopyUseCase {
    operator fun invoke(model: AddressModel, uiModel: AddressSuggestUiModel) = model.copy(
        city = uiModel.city,
        house = uiModel.house,
        street = uiModel.street,
        comment = uiModel.comment
    )
}