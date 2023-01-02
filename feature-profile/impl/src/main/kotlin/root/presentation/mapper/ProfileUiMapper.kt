package root.presentation.mapper

import domain.GetMaskedPhoneUseCase
import domain.ProfileModel
import root.presentation.compose.model.ProfileUiModel
import utils.CommonConstants.Helpers.LINE_BREAK
import utils.CommonConstants.Helpers.SPACER

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
        append(profile.surname + LINE_BREAK + profile.name + SPACER + profile.secondName)
    }
}