package organization.company.presentation.viewmodel.model

import state.registration.CompanyParamState

data class CompanyState(
    val companyName: CompanyParamState.NameState = CompanyParamState.NameState(),
    val inn: CompanyParamState.InnState = CompanyParamState.InnState(),
    val kpp: CompanyParamState.KppState = CompanyParamState.KppState(),
    val ogrn: CompanyParamState.OgrnState = CompanyParamState.OgrnState(),
    val legalAddress: CompanyParamState.LegalAddressState = CompanyParamState.LegalAddressState(),
    val actualAddress: CompanyParamState.ActualAddressState = CompanyParamState.ActualAddressState(),
    val isContinueButtonEnabled: Boolean = false
)