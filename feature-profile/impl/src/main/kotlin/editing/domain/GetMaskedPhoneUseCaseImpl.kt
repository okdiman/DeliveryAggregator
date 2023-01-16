package editing.domain

import androidx.compose.ui.text.AnnotatedString
import domain.usecase.GetMaskedPhoneUseCase
import utils.CommonConstants.LIMITS.Common.MAX_PHONE_CHARS
import utils.CommonConstants.MASK.PHONE
import utils.DigitVisualTransformation

class GetMaskedPhoneUseCaseImpl : GetMaskedPhoneUseCase {
    private val digitVisualTransformation =
        DigitVisualTransformation.create(mask = PHONE, maxChar = MAX_PHONE_CHARS)

    override fun invoke(phone: String): String {
        val maskedPhone = digitVisualTransformation.filter(AnnotatedString(phone)).text.text
        return buildString {
            append(PHONE_CODE)
            append(maskedPhone)
        }
    }

    companion object {
        private const val PHONE_CODE = "+7 "
    }
}