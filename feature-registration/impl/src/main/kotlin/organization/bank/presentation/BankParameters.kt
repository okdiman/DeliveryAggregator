package organization.bank.presentation

import androidx.compose.runtime.Immutable
import root.presentation.model.RegistrationCompanyModel
import presentation.parameters.model.RegistrationStartUserModel

@Immutable
class BankParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel
)