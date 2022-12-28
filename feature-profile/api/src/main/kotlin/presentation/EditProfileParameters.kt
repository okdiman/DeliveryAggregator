package presentation

import androidx.compose.runtime.Immutable
import domain.ProfileModel

@Immutable
class EditProfileParameters(
    val profileModel: ProfileModel
)