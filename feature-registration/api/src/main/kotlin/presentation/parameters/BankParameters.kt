package presentation.parameters

import androidx.compose.runtime.Immutable
import presentation.parameters.model.RegistrationCompanyModel
import presentation.parameters.model.RegistrationStartUserModel

@Immutable
class BankParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel
)