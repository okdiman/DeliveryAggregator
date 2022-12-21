package organization.company.presentation.viewmodel

import BaseViewModel
import organization.company.presentation.viewmodel.model.CompanyAction
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import root.RegistrationConstants.Limits.Common.MIN_ADDRESS_CHARS
import root.RegistrationConstants.Limits.Common.MIN_NAME_CHARS
import root.RegistrationConstants.Limits.Company.INN_CHARS
import root.RegistrationConstants.Limits.Company.KPP_CHARS
import root.RegistrationConstants.Limits.Company.OGRN_CHARS
import utils.isTextFieldFilled

class CompanyViewModel : BaseViewModel<CompanyState, CompanyAction, CompanyEvent>(
    initialState = CompanyState()
) {

    override fun obtainEvent(viewEvent: CompanyEvent) {
        when (viewEvent) {
            is CompanyEvent.OnNameChanged -> onNameChanged(viewEvent.name)
            is CompanyEvent.OnInnChanged -> onInnChanged(viewEvent.inn)
            is CompanyEvent.OnKppChanged -> onKppChanged(viewEvent.kpp)
            is CompanyEvent.OnOgrnChanged -> onOgrnChanged(viewEvent.ogrn)
            is CompanyEvent.OnLegalAddressChanged -> onLegalAddressChanged(viewEvent.legalAddress)
            is CompanyEvent.OnActualAddressChanged -> onActualAddressChanged(viewEvent.actualAddress)
            is CompanyEvent.OnContinueButtonClick -> onContinueButtonClick()
            is CompanyEvent.ResetAction -> onResetAction()
        }
    }

    private fun onContinueButtonClick() {
        viewAction = CompanyAction.OpenNextStep
    }

    private fun onActualAddressChanged(newActualAddress: String) {
        viewState = viewState.copy(
            actualAddress = viewState.actualAddress.copy(
                text = newActualAddress,
                isAddressError = !isTextFieldFilled(newActualAddress, MIN_ADDRESS_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(actualAddress = newActualAddress)
        )
    }

    private fun onLegalAddressChanged(newLegalAddress: String) {
        viewState = viewState.copy(
            legalAddress = viewState.legalAddress.copy(
                text = newLegalAddress,
                isAddressError = !isTextFieldFilled(newLegalAddress, MIN_ADDRESS_CHARS)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(legalAddress = newLegalAddress)
        )
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
                isInnError = !isTextFieldFilled(newInn, INN_CHARS)
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