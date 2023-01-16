package presentation.parameters

import androidx.compose.runtime.Immutable
import presentation.parameters.model.RegistrationBankModel
import presentation.parameters.model.RegistrationCompanyModel
import presentation.parameters.model.RegistrationStartUserModel

@Immutable
class TransportParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel,
    val bank: RegistrationBankModel
)