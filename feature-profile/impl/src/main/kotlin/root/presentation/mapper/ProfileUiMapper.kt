package root.presentation.mapper

import domain.GetMaskedPhoneUseCase
import domain.ProfileModel
import root.presentation.compose.model.ProfileUiModel

class ProfileUiMapper(
    private val getMaskedPhone: GetMaskedPhoneUseCase
) {
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
}