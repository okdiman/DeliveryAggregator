package organization.company.presentation.viewmodel.model

sealed class CompanyEvent {
    data class OnNameChanged(val name: String) : CompanyEvent()
    data class OnInnChanged(val inn: String) : CompanyEvent()
    data class OnKppChanged(val kpp: String) : CompanyEvent()
    data class OnOgrnChanged(val ogrn: String) : CompanyEvent()
    data class OnLegalAddressChanged(val legalAddress: String) : CompanyEvent()
    data class OnActualAddressChanged(val actualAddress: String) : CompanyEvent()
    object OnContinueButtonClick : CompanyEvent()
    object ResetAction : CompanyEvent()
}