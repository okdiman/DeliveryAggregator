package organization.bank.presentation

import androidx.compose.runtime.Immutable
import presentation.parameters.model.RegistrationStartUserModel
import root.presentation.RegistrationCompanyModel

@Immutable
class BankParameters(
    val user: RegistrationStartUserModel,
    val company: RegistrationCompanyModel
)