package user.presentation

import androidx.compose.runtime.Immutable
import root.presentation.model.RegistrationBankModel
import root.presentation.model.RegistrationCompanyModel
import presentation.parameters.model.RegistrationStartUserModel
import root.presentation.model.RegistrationTransportModel

@Immutable
class UserParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel,
    val bank: RegistrationBankModel,
    val transport: RegistrationTransportModel
)