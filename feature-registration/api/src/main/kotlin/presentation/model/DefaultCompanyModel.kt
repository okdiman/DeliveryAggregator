package presentation.model

import androidx.compose.runtime.Immutable

@Immutable
class DefaultCompanyModel(
    val companyName: String,
    val inn: String,
    val kpp: String,
    val ogrn: String,
    val legalAddress: String,
    val actualAddress: String
)