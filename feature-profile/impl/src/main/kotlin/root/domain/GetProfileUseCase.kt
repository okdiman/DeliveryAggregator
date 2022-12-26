package root.domain

import androidx.compose.ui.text.AnnotatedString
import data.ProfileRepository
import domain.ProfileModel
import root.presentation.compose.model.ProfileUiModel
import utils.CommonConstants.LIMITS.MAX_PHONE_CHARS
import utils.CommonConstants.MASK.PHONE
import utils.DigitVisualTransformation

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    private val digitVisualTransformation =
        DigitVisualTransformation.create(mask = PHONE, maxChar = MAX_PHONE_CHARS)

    suspend operator fun invoke(): ProfileUiModel {
        val profile = repository.getProfile()
        return ProfileUiModel(
            name = getFullName(profile),
            organizationName = profile.organisationName,
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