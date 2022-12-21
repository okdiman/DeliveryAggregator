package presentation.parameters

import androidx.compose.runtime.Immutable
import presentation.model.DefaultBankModel
import presentation.model.DefaultCompanyModel
import presentation.model.DefaultUserModel

@Immutable
class TransportParameters(
    val user: DefaultUserModel,
    val company: DefaultCompanyModel,
    val bank: DefaultBankModel
)