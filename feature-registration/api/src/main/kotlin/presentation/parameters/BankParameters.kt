package presentation.parameters

import androidx.compose.runtime.Immutable
import presentation.model.DefaultCompanyModel
import presentation.model.DefaultUserModel

@Immutable
class BankParameters(
    val user: DefaultUserModel,
    val company: DefaultCompanyModel
)