package departure.domain

import domain.model.AddressModel
import presentation.model.AddressUiModel

class AddressModelCopyUseCase {
    operator fun invoke(model: AddressModel, uiModel: AddressUiModel) = model.copy(
        city = uiModel.city,
        house = uiModel.house,
        street = uiModel.street,
        comment = uiModel.comment
    )
}