package offer.domain

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import offer.domain.model.OfferModel
import trinity_monsters.delivery_aggregator.feature_auth.impl.R
import utils.resource.domain.ResourceInteractor

class GetOfferUseCase(
    private val resourceInteractor: ResourceInteractor,
) {
    operator fun invoke(): String {
        val offerSource = resourceInteractor.getStringFromRawResource(R.raw.offer)
        val model: OfferModel = Json.decodeFromString(offerSource)
        return model.offer
    }
}
