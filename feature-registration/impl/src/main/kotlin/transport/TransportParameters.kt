package transport

import androidx.compose.runtime.Immutable
import presentation.parameters.model.RegistrationStartUserModel
import root.presentation.model.RegistrationBankModel
import root.presentation.model.RegistrationCompanyModel

@Immutable
class TransportParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel,
    val bank: RegistrationBankModel
)