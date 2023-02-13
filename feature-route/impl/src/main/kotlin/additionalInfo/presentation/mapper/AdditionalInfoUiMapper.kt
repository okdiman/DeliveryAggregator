package additionalInfo.presentation.mapper

import additionalInfo.presentation.AdditionalInfoParameters
import additionalInfo.presentation.compose.model.AdditionalInfoUiModel
import orderdetails.root.domain.model.extras.OrderDetailsExtrasModel
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.Helpers.COMMA
import utils.CommonConstants.Helpers.LINE_BREAK
import utils.resource.domain.ResourceInteractor

class AdditionalInfoUiMapper(
    private val resourceInteractor: ResourceInteractor
) {
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
            text = mapExtras(parameters.extras).joinToString(LINE_BREAK)
        ),
        AdditionalInfoUiModel(
            title = R.string.additional_info_comment,
            text = parameters.comment.ifEmpty { resourceInteractor.getString(R.string.additional_info_comment_empty) }
        )
    )

    private fun mapExtras(extrasModel: List<OrderDetailsExtrasModel>?): List<String> {
        return extrasModel?.map {
            if (it.priceDescription.isValid) {
                buildString { append(it.name + COMMA + it.priceDescription.text) }
            } else buildString { append(it.name + COMMA + it.price) }
        } ?: listOf(resourceInteractor.getString(R.string.additional_info_dont_need))
    }
}