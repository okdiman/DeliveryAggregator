package login.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import domain.model.VerifyCodeModel
import domain.usecase.GetCodeUseCase
import login.presentation.viewmodel.model.LoginAction
import login.presentation.viewmodel.model.LoginEvent
import login.presentation.viewmodel.model.LoginState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : BaseViewModel<LoginState, LoginAction, LoginEvent>(
    initialState = LoginState()
), KoinComponent {
    private val getVerifyCode by inject<GetCodeUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is LoginEvent.OnAgreementClick -> onAgreementClick()
            is LoginEvent.OnOfferCLick -> onOfferClick()
            is LoginEvent.OnEntranceButtonCLick -> onEntranceButtonClick()
            is LoginEvent.PhoneChanged -> onPhoneChanged(viewEvent.phone)
            is LoginEvent.ResetAction -> onResetAction()
        }
    }

    private fun onAgreementClick() {
        viewState = viewState.copy(
            isAgreementChecked = !viewState.isAgreementChecked,
            isButtonEnabled = isButtonEnabled(!viewState.isAgreementChecked)
        )
    }

    private fun onPhoneChanged(newPhone: String) {
        viewState = viewState.copy(
            phone = newPhone,
            isButtonEnabled = isButtonEnabled(
                phone = newPhone
            )
        )
    }

    private fun onOfferClick() {
        viewAction = LoginAction.OpenOffer
    }

    private fun onEntranceButtonClick() {
        launchJob(appDispatchers.network) {
            getVerifyCode(VerifyCodeModel(viewState.phone))
        }
        viewAction = LoginAction.OpenVerifyScreen
    }

    private fun isButtonEnabled(
        isAgreementChecked: Boolean = viewState.isAgreementChecked,
        phone: String = viewState.phone
    ): Boolean {
        return isAgreementChecked && phone.length == PHONE_LENGTH
    }

    companion object {
        private const val PHONE_LENGTH = 10
    }
}