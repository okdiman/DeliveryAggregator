package organization.company.presentation.viewmodel.model

import organization.company.presentation.compose.model.CompanyParamState
import presentation.AddressUiModel

data class CompanyState(
    val companyName: CompanyParamState.NameState = CompanyParamState.NameState(),
    val inn: CompanyParamState.InnState = CompanyParamState.InnState(),
    val kpp: CompanyParamState.KppState = CompanyParamState.KppState(),
    val ogrn: CompanyParamState.OgrnState = CompanyParamState.OgrnState(),
    val legalAddress: CompanyParamState.LegalAddressState = CompanyParamState.LegalAddressState(),
    val actualAddress: CompanyParamState.ActualAddressState = CompanyParamState.ActualAddressState(),
    val bsAddress: CompanyParamState.BsAddressState = CompanyParamState.BsAddressState(),
    val addressList: List<AddressUiModel> = emptyList(),
    val isContinueButtonEnabled: Boolean = false
)