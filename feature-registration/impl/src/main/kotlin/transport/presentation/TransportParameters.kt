package transport.presentation

import androidx.compose.runtime.Immutable
import root.presentation.model.RegistrationBankModel
import root.presentation.model.RegistrationCompanyModel
import presentation.parameters.model.RegistrationStartUserModel

@Immutable
class TransportParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel,
    val bank: RegistrationBankModel
)