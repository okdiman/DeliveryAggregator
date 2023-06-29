package root.presentation

import androidx.compose.runtime.Immutable
import root.presentation.model.RegistrationFullAddressModel

@Immutable
class RegistrationCompanyModel(
    val companyName: String,
    val inn: String,
    val kpp: String,
    val ogrn: String,
    val legalAddress: String?,
    val actualAddress: String?,
    val fullAddress: RegistrationFullAddressModel
)