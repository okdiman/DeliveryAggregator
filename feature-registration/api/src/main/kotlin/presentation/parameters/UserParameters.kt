package presentation.parameters

import androidx.compose.runtime.Immutable
import presentation.model.RegistrationBankModel
import presentation.model.RegistrationCompanyModel
import presentation.model.RegistrationStartUserModel
import presentation.model.RegistrationTransportModel

@Immutable
class UserParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel,
    val bank: RegistrationBankModel,
    val transport: RegistrationTransportModel
)