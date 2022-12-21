package presentation.parameters

import androidx.compose.runtime.Immutable
import presentation.model.RegistrationCompanyModel
import presentation.model.RegistrationStartUserModel

@Immutable
class BankParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel
)