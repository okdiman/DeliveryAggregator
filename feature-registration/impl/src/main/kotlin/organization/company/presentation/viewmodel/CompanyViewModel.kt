package organization.company.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import data.AddressConstants.DEBOUNCE
import data.AddressConstants.MIN_CHARS_FOR_SUGGEST
import domain.model.AddressSuggestRequestModel
import domain.usecase.GetSuggestByQueryUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.company.presentation.viewmodel.model.CompanyAction
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import presentation.AddressUiModel
import presentation.mapper.AddressSuggestUiMapper
import presentation.parameters.CompanyParameters
import root.RegistrationConstants.Limits.Company.INN_CHARS
import root.RegistrationConstants.Limits.Company.INN_MIN_CHARS
import root.RegistrationConstants.Limits.Company.KPP_CHARS
import root.RegistrationConstants.Limits.Company.OGRN_CHARS
import utils.CommonConstants.LIMITS.Common.MIN_ADDRESS_CHARS
import utils.CommonConstants.LIMITS.Common.MIN_NAME_CHARS
import utils.isTextFieldFilled

class CompanyViewModel(
    private val parameters: CompanyParameters
) : BaseViewModel<CompanyState, CompanyAction, CompanyEvent>(initialState = CompanyState()),
    KoinComponent {

    private val getSuggestByQuery by inject<GetSuggestByQueryUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val addressUiMapper by inject<AddressSuggestUiMapper>()

    private var suggestJob: Job? = null

    override fun obtainEvent(viewEvent: CompanyEvent) {
        when (viewEvent) {
            is CompanyEvent.OnCompanyNameChanged -> onNameChanged(viewEvent.name)
            is CompanyEvent.OnInnChanged -> onInnChanged(viewEvent.inn)
            is CompanyEvent.OnKppChanged -> onKppChanged(viewEvent.kpp)
            is CompanyEvent.OnOgrnChanged -> onOgrnChanged(viewEvent.ogrn)
            is CompanyEvent.OnLegalSuggestAddressClick -> onLegalAddressSuggestClick(viewEvent.address)
            is CompanyEvent.OnActualSuggestAddressClick -> onActualAddressSuggestClick(viewEvent.address)
            is CompanyEvent.OnBSAddressChanged -> onBsAddressChanged(viewEvent.bsAddress)
            CompanyEvent.OnLegalAddressClick -> onLegalAddressClick()
            CompanyEvent.OnActualAddressClick -> onActualAddressClick()
            CompanyEvent.OnContinueButtonClick -> onContinueButtonClick()
            CompanyEvent.ResetAction -> {
                onResetAction()
                resetSuggests()
            }
        }
    }

    private fun onBsAddressChanged(address: String) {
        loadSuggests(address)
        viewState = viewState.copy(
            bsAddress = viewState.bsAddress.copy(
                text = address
            )
        )
    }

    private fun onLegalAddressSuggestClick(address: AddressUiModel) {
        viewState = viewState.copy(
            legalAddress = viewState.legalAddress.copy(
                text = address.value,
                address = address,
                isAddressError = address.house.isEmpty()
            ),
            bsAddress = viewState.bsAddress.copy(
                text = address.subtitle
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(legalAddress = address.value)
        )
    }

    private fun onActualAddressSuggestClick(address: AddressUiModel) {
        viewState = viewState.copy(
            actualAddress = viewState.actualAddress.copy(
                text = address.value,
                address = address
            ),
            bsAddress = viewState.bsAddress.copy(
                text = address.subtitle
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(actualAddress = address.value)
        )
    }

    private fun resetSuggests() {
        viewState = viewState.copy(suggests = emptyList())
    }

    private fun onLegalAddressClick() {
        viewAction = CompanyAction.OpenSelectLegalAddress
    }

    private fun onActualAddressClick() {
        viewAction = CompanyAction.OpenSelectActualAddress
    }

    private fun onContinueButtonClick() {
        viewAction = CompanyAction.OpenNextStep
    }

    private fun onOgrnChanged(newOgrn: String) {
        viewState = viewState.copy(
            ogrn = viewState.ogrn.copy(
                text = newOgrn,
                isOgrnError = !isTextFieldFilled(newOgrn, OGRN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(ogrn = newOgrn)
        )
    }

    private fun onKppChanged(newKpp: String) {
        viewState = viewState.copy(
            kpp = viewState.kpp.copy(
                text = newKpp,
                isKppError = !isTextFieldFilled(newKpp, KPP_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(kpp = newKpp)
        )
    }

    private fun onInnChanged(newInn: String) {
        viewState = viewState.copy(
            inn = viewState.inn.copy(
                text = newInn,
                isInnError = !isTextFieldFilled(newInn, INN_MIN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(inn = newInn)
        )
    }

    private fun onNameChanged(newName: String) {
        viewState = viewState.copy(
            companyName = viewState.companyName.copy(
                text = newName,
                isNameError = !isTextFieldFilled(newName, MIN_NAME_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(name = newName)
        )
    }

    private fun loadSuggests(query: String) {
        suggestJob?.cancel()
        if (query.length >= MIN_CHARS_FOR_SUGGEST) {
            suggestJob = launchJob(appDispatchers.network) {
                delay(DEBOUNCE)
                val suggests = getSuggestByQuery(
                    AddressSuggestRequestModel(
                        query = query,
                        code = parameters.user.code.toInt(),
                        phone = parameters.user.phone
                    )
                )
                viewState = viewState.copy(suggests = addressUiMapper.map(suggests))
            }
        } else {
            viewState = viewState.copy(suggests = emptyList())
        }
    }

    private fun isContinueButtonEnabled(
        inn: String = viewState.inn.text,
        kpp: String = viewState.kpp.text,
        ogrn: String = viewState.ogrn.text,
        name: String = viewState.companyName.text,
        legalAddress: String = viewState.legalAddress.text,
        actualAddress: String = viewState.actualAddress.text
    ) = isTextFieldFilled(actualAddress, MIN_ADDRESS_CHARS) &&
            isTextFieldFilled(legalAddress, MIN_ADDRESS_CHARS) &&
            isTextFieldFilled(ogrn, OGRN_CHARS) && isTextFieldFilled(kpp, KPP_CHARS) &&
            isTextFieldFilled(inn, INN_CHARS) && isTextFieldFilled(name, MIN_NAME_CHARS)
}