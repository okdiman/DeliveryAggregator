package offer.domain

import com.google.gson.Gson
import offer.domain.model.OfferModel
import trinity_monsters.delivery_aggregator.feature_auth.impl.R
import utils.resource.domain.ResourceInteractor

class GetOfferUseCase(
    private val resourceInteractor: ResourceInteractor,
    private val gson: Gson,
) {
    operator fun invoke(): String {
        val offerSource = resourceInteractor.getStringFromRawResource(R.raw.offer)
        val model = gson.fromJson(offerSource, OfferModel::class.java)
        return model.offer
    }
}