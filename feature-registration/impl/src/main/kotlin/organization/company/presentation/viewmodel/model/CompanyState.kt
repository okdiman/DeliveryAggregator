package organization.company.presentation.viewmodel.model

data class CompanyState(
    val name: String = "",
    val inn: String = "",
    val kpp: String = "",
    val ogrn: String = "",
    val legalAddress: String = "",
    val actualAddress: String = "",
    val isContinueButtonEnabled: Boolean = false
)