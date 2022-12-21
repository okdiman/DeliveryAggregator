package presentation.parameters

import androidx.compose.runtime.Immutable
import presentation.model.RegistrationBankModel
import presentation.model.RegistrationCompanyModel
import presentation.model.RegistrationStartUserModel

@Immutable
class TransportParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel,
    val bank: RegistrationBankModel
)