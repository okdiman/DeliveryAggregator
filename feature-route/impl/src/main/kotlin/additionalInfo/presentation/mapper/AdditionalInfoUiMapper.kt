package additionalInfo.presentation.mapper

import additionalInfo.presentation.AdditionalInfoParameters
import additionalInfo.presentation.compose.model.AdditionalInfoUiModel
import trinity_monsters.wildberries_delivery_aggregator.feature_route.impl.R

class AdditionalInfoUiMapper {
    fun map(parameters: AdditionalInfoParameters) = listOf(
        AdditionalInfoUiModel(
            title = R.string.additional_info_marketplace_name,
            text = parameters.marketplace
        ),
        AdditionalInfoUiModel(
            title = R.string.additional_info_organization_name,
            text = parameters.organization
        ),
        AdditionalInfoUiModel(
            title = R.string.additional_info_boxes_count,
            text = parameters.boxesCount
        ),
        AdditionalInfoUiModel(
            title = R.string.additional_info_weight,
            text = parameters.weight
        ),
        AdditionalInfoUiModel(
            title = R.string.additional_info_additional_services,
            text = parameters.additionalOptions
        ),
        AdditionalInfoUiModel(
            title = R.string.additional_info_comment,
            text = parameters.comment
        )
    )
}