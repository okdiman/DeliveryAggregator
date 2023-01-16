package presentation.parameters

import androidx.compose.runtime.Immutable
import presentation.parameters.model.RegistrationBankModel
import presentation.parameters.model.RegistrationCompanyModel
import presentation.parameters.model.RegistrationStartUserModel
import presentation.parameters.model.RegistrationTransportModel

@Immutable
class UserParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel,
    val bank: RegistrationBankModel,
    val transport: RegistrationTransportModel
)