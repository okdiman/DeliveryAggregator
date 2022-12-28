package root.presentation.mapper

import androidx.compose.ui.text.AnnotatedString
import domain.ProfileModel
import root.presentation.compose.model.ProfileUiModel
import utils.CommonConstants.LIMITS.Common.MAX_PHONE_CHARS
import utils.CommonConstants.MASK.PHONE
import utils.DigitVisualTransformation

class ProfileUiMapper {
    private val digitVisualTransformation =
        DigitVisualTransformation.create(mask = PHONE, maxChar = MAX_PHONE_CHARS)

    fun map(profile: ProfileModel): ProfileUiModel {
        return ProfileUiModel(
            name = getFullName(profile),
            organizationName = profile.organizationName,
            email = profile.email,
            phone = getMaskedPhone(profile.phone)
        )
    }

    private fun getFullName(profile: ProfileModel) = buildString {
        append(profile.surname)
        append("\n")
        append(profile.name)
        append(" ")
        append(profile.secondName)
    }

    private fun getMaskedPhone(phone: String): String {
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