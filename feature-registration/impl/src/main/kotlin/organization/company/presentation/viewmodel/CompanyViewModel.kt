package organization.company.presentation.viewmodel

import BaseViewModel
import organization.company.presentation.viewmodel.model.CompanyAction
import organization.company.presentation.viewmodel.model.CompanyEvent
import organization.company.presentation.viewmodel.model.CompanyState
import root.RegistrationConstants.Limits.MAX_INN_CHARS
import root.RegistrationConstants.Limits.MAX_KPP_CHARS
import root.RegistrationConstants.Limits.MAX_OGRN_CHARS
import root.RegistrationConstants.Limits.MIN_ADDRESS_CHARS
import root.RegistrationConstants.Limits.MIN_NAME_CHARS

@Suppress("TooManyFunctions")
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

    private fun onResetAction() {
        viewAction = null
    }

    private fun onContinueButtonClick() {
        viewAction = CompanyAction.OpenNextStep
    }

    private fun onActualAddressChanged(newActualAddress: String) {
        viewState = viewState.copy(
            actualAddress = viewState.actualAddress.copy(
                text = newActualAddress,
                isAddressError = !isActualAddressFilled(newActualAddress)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(actualAddress = newActualAddress)
        )
    }

    private fun onLegalAddressChanged(newLegalAddress: String) {
        viewState = viewState.copy(
            legalAddress = viewState.legalAddress.copy(
                text = newLegalAddress,
                isAddressError = !isLegalAddressFilled(newLegalAddress)
            ),
            isContinueButtonEnabled = isContinueButtonEnabled(legalAddress = newLegalAddress)
        )
    }

    private fun onOgrnChanged(newOgrn: String) {
        viewState = viewState.copy(
            ogrn = viewState.ogrn.copy(text = newOgrn, isOgrnError = !isOgrnFilled(newOgrn)),
            isContinueButtonEnabled = isContinueButtonEnabled(ogrn = newOgrn)
        )
    }

    private fun onKppChanged(newKpp: String) {
        viewState = viewState.copy(
            kpp = viewState.kpp.copy(text = newKpp, isKppError = !isKppFilled(newKpp)),
            isContinueButtonEnabled = isContinueButtonEnabled(kpp = newKpp)
        )
    }

    private fun onInnChanged(newInn: String) {
        viewState = viewState.copy(
            inn = viewState.inn.copy(text = newInn, isInnError = !isInnFilled(newInn)),
            isContinueButtonEnabled = isContinueButtonEnabled(inn = newInn)
        )
    }

    private fun onNameChanged(newName: String) {
        viewState = viewState.copy(
            companyName = viewState.companyName.copy(text = newName, isNameError = !isNameFilled(newName)),
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
    ): Boolean {
        return isInnFilled(inn) && isKppFilled(kpp) && isOgrnFilled(ogrn) && isNameFilled(name) &&
                isLegalAddressFilled(legalAddress) && isActualAddressFilled(actualAddress)
    }

    private fun isInnFilled(inn: String) = inn.length == MAX_INN_CHARS
    private fun isKppFilled(kpp: String) = kpp.length == MAX_KPP_CHARS
    private fun isOgrnFilled(ogrn: String) = ogrn.length == MAX_OGRN_CHARS
    private fun isNameFilled(name: String) = name.length >= MIN_NAME_CHARS
    private fun isLegalAddressFilled(legalAddress: String) =
        legalAddress.length >= MIN_ADDRESS_CHARS

    private fun isActualAddressFilled(actualAddress: String) =
        actualAddress.length >= MIN_ADDRESS_CHARS
}