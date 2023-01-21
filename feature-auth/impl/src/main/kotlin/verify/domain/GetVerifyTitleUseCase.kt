package verify.domain

import trinity_monsters.wildberries_delivery_aggregator.feature_auth.impl.R
import utils.CommonConstants.Helpers.LINE_BREAK
import utils.resource.domain.ResourceInteractor

class GetVerifyTitleUseCase(
    private val resourceInteractor: ResourceInteractor
) {
    operator fun invoke(phone: String): String {
        return buildString {
            append(resourceInteractor.getString(R.string.verify_title_start))
            append(LINE_BREAK + maskedPhone(phone) + LINE_BREAK)
            append(resourceInteractor.getString(R.string.verify_title_end))
        }
    }

    private fun maskedPhone(phone: String): String {
        return buildString {
            append(resourceInteractor.getString(R.string.login_phone_prefix))
            append(
                FRONT_BRACKET + phone.take(FIRST_THREE) + BACK_BRACKET + MASK +
                        phone.takeLast(LAST_TWO) + POINT
            )
        }
    }

    companion object {
        private const val MASK = "***-**-"
        private const val FRONT_BRACKET = "("
        private const val BACK_BRACKET = ")"
        private const val POINT = "."
        private const val FIRST_THREE = 3
        private const val LAST_TWO = 2
    }
}