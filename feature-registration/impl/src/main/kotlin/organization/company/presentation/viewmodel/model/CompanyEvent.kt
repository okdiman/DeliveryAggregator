package organization.company.presentation.viewmodel.model

import presentation.AddressUiModel

sealed interface CompanyEvent {
    data class OnCompanyNameChanged(val name: String) : CompanyEvent
    data class OnInnChanged(val inn: String) : CompanyEvent
    data class OnKppChanged(val kpp: String) : CompanyEvent
    data class OnOgrnChanged(val ogrn: String) : CompanyEvent
    data class OnBSAddressChanged(val bsAddress: String) : CompanyEvent
    data class OnActualSuggestAddressClick(val address: AddressUiModel) : CompanyEvent
    data class OnLegalSuggestAddressClick(val address: AddressUiModel) : CompanyEvent
    object OnLegalAddressClick : CompanyEvent
    object OnActualAddressClick : CompanyEvent
    object OnContinueButtonClick : CompanyEvent
    object ResetAction : CompanyEvent
}