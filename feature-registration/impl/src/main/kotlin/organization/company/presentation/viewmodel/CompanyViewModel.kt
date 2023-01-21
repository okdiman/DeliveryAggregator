package organization.company.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import data.AddressConstants.DEBOUNCE
import data.AddressConstants.MIN_CHARS_FOR_SUGGEST
import di.modules.NAMING_VALIDATOR_QUALIFIER
import domain.GetAuthSuggestByQueryUseCase
import domain.model.request.AddressAuthSuggestRequestModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import organization.company.presentation.viewmodel.model.CompanyAction
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import presentation.mapper.AddressSuggestUiMapper
import presentation.model.AddressUiModel
import presentation.parameters.CompanyParameters
import root.RegistrationConstants.Limits.Company.INN_MIN_CHARS
import root.RegistrationConstants.Limits.Company.KPP_CHARS
import root.RegistrationConstants.Limits.Company.OGRN_CHARS
import utils.CommonConstants.LIMITS.Common.MIN_ADDRESS_CHARS
import utils.CommonConstants.LIMITS.Common.MIN_NAME_CHARS
import utils.isTextFieldFilled
import utils.validators.domain.TextFieldValidator

class CompanyViewModel(
    private val parameters: CompanyParameters
) : BaseViewModel<CompanyState, CompanyAction, CompanyEvent>(initialState = CompanyState()),
    KoinComponent {

    private val getSuggestByQuery by inject<GetAuthSuggestByQueryUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val addressUiMapper by inject<AddressSuggestUiMapper>()
    private val namingValidator by inject<TextFieldValidator>(named(NAMING_VALIDATOR_QUALIFIER))

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
                stateText = address
            )
        )
    }

    private fun onLegalAddressSuggestClick(address: AddressUiModel) {
        viewState = viewState.copy(
            legalAddress = viewState.legalAddress.copy(
                stateText = address.value,
                address = address,
                isFillingError = address.house.isEmpty()
            ),
            bsAddress = viewState.bsAddress.copy(
                stateText = address.subtitle
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(legalAddress = viewState.legalAddress.copy(stateText = address.value))
            )
        )
    }

    private fun onActualAddressSuggestClick(address: AddressUiModel) {
        viewState = viewState.copy(
            actualAddress = viewState.actualAddress.copy(
                stateText = address.value,
                address = address
            ),
            bsAddress = viewState.bsAddress.copy(
                stateText = address.subtitle
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(actualAddress = viewState.actualAddress.copy(stateText = address.value))
            )
        )
    }

    private fun resetSuggests() {
        viewState = viewState.copy(suggests = emptyList())
    }

    private fun onLegalAddressClick() {
        viewState = viewState.copy(
            bsAddress = viewState.bsAddress.copy(stateText = viewState.legalAddress.stateText)
        )
        viewAction = CompanyAction.OpenSelectLegalAddress
    }

    private fun onActualAddressClick() {
        viewState = viewState.copy(
            bsAddress = viewState.bsAddress.copy(stateText = viewState.actualAddress.stateText)
        )
        viewAction = CompanyAction.OpenSelectActualAddress
    }

    private fun onContinueButtonClick() {
        viewAction = CompanyAction.OpenNextStep
    }

    private fun onOgrnChanged(newOgrn: String) {
        viewState = viewState.copy(
            ogrn = viewState.ogrn.copy(
                stateText = newOgrn,
                isFillingError = !isTextFieldFilled(newOgrn, OGRN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(ogrn = viewState.ogrn.copy(stateText = newOgrn))
            )
        )
    }

    private fun onKppChanged(newKpp: String) {
        viewState = viewState.copy(
            kpp = viewState.kpp.copy(
                stateText = newKpp,
                isFillingError = !isTextFieldFilled(newKpp, KPP_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(kpp = viewState.kpp.copy(stateText = newKpp))
            )
        )
    }

    private fun onInnChanged(newInn: String) {
        viewState = viewState.copy(
            inn = viewState.inn.copy(
                stateText = newInn,
                isFillingError = !isTextFieldFilled(newInn, INN_MIN_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(inn = viewState.inn.copy(stateText = newInn))
            )
        )
    }

    private fun onNameChanged(newName: String) {
        val isValid = namingValidator.isValidate(newName)
        viewState = viewState.copy(
            companyName = viewState.companyName.copy(
                stateText = newName,
                isFillingError = !isTextFieldFilled(newName, MIN_NAME_CHARS),
                isValidationError = !isValid
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(
                viewState.copy(
                    companyName = viewState.companyName.copy(
                        stateText = newName,
                        isValidationError = !isValid
                    )
                )
            )
        )
    }

    private fun loadSuggests(query: String) {
        suggestJob?.cancel()
        if (query.length >= MIN_CHARS_FOR_SUGGEST) {
            suggestJob = launchJob(context = appDispatchers.network, onError = {
                viewState = viewState.copy(
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
                )
            }) {
                delay(DEBOUNCE)
                viewState = viewState.copy(
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = true)
                )
                val suggests = getSuggestByQuery(
                    AddressAuthSuggestRequestModel(
                        query = query,
                        code = parameters.user.code.toInt(),
                        phone = parameters.user.phone
                    )
                )
                viewState = viewState.copy(
                    suggests = addressUiMapper.map(suggests),
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
                )
            }
        } else {
            viewState = viewState.copy(
                suggests = emptyList(),
                bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
            )
        }
    }

    private fun isContinueButtonEnabled(state: CompanyState) =
        isTextFieldFilled(state.actualAddress.stateText, MIN_ADDRESS_CHARS) &&
            isTextFieldFilled(state.legalAddress.stateText, MIN_ADDRESS_CHARS) &&
            isTextFieldFilled(state.ogrn.stateText, OGRN_CHARS) &&
            isTextFieldFilled(state.kpp.stateText, KPP_CHARS) &&
            isTextFieldFilled(state.inn.stateText, INN_MIN_CHARS) &&
            isTextFieldFilled(state.companyName.stateText, MIN_NAME_CHARS) &&
            !state.companyName.isValidationError
}