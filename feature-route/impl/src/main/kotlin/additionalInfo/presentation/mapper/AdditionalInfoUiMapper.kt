package additionalInfo.presentation.mapper

import additionalInfo.presentation.AdditionalInfoParameters
import additionalInfo.presentation.compose.model.AdditionalInfoUiModel
import root.domain.model.extras.OrderExtrasModel
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.Helpers.COMMA
import utils.CommonConstants.Helpers.LINE_BREAK
import utils.CommonConstants.Helpers.SPACER
import utils.resource.domain.ResourceInteractor
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

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
            title = R.string.route_boxes_count,
            text = parameters.boxesCount
        ),
        AdditionalInfoUiModel(
            title = R.string.route_weight,
            text = buildString {
                append(parameters.weight + SPACER + resourceInteractor.getString(R.string.route_weight_unit))
            }
        ),
        AdditionalInfoUiModel(
            title = R_core.string.common_extras,
            text = mapExtras(parameters.extras).joinToString(LINE_BREAK)
        ),
        AdditionalInfoUiModel(
            title = R_core.string.common_info_comment,
            text = parameters.comment.ifEmpty { resourceInteractor.getString(R.string.additional_info_comment_empty) }
        )
    )

    private fun mapExtras(extrasModel: List<OrderExtrasModel>?) = extrasModel?.map {
        if (it.priceDescription.isValid) {
            buildString {
                append(
                    it.name + COMMA + it.priceDescription.text + resourceInteractor.getString(
                        R.string.additional_info_factor,
                        it.count
                    )
                )
            }
        } else {
            buildString {
                append(
                    it.name + COMMA + it.price.toInt() + resourceInteractor.getString(
                        R.string.additional_info_factor,
                        it.count
                    )
                )
            }
        }
    } ?: listOf(resourceInteractor.getString(R.string.additional_info_dont_need))
}