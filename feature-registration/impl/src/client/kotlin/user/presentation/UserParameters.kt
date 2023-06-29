package user.presentation

import androidx.compose.runtime.Immutable
import presentation.parameters.model.RegistrationStartUserModel
import root.presentation.RegistrationCompanyModel
import root.presentation.model.RegistrationBankModel

@Immutable
class UserParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel,
    val bank: RegistrationBankModel
)