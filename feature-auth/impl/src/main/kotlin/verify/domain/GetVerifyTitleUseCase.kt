package verify.domain

import trinity_monsters.wildberries_delivery_aggregator.feature_auth.impl.R
import utils.resource.domain.ResourceInteractor

class GetVerifyTitleUseCase(
    private val resourceInteractor: ResourceInteractor
) {
    operator fun invoke(phone: String): String {
        return buildString {
            append(resourceInteractor.getString(R.string.verify_title_start))
            append("\n")
            append(maskedPhone(phone))
            append("\n")
            append(resourceInteractor.getString(R.string.verify_title_end))
        }
    }

    private fun maskedPhone(phone: String): String {
        return buildString {
            append(resourceInteractor.getString(R.string.phone_prefix))
            append("(")
            append(phone.take(FIRST_THREE))
            append(")")
            append(MASK)
            append(phone.takeLast(LAST_TWO))
            append(".")
        }
    }

    companion object {
        private const val MASK = "***-**-"
        private const val FIRST_THREE = 3
        private const val LAST_TWO = 2
    }
}