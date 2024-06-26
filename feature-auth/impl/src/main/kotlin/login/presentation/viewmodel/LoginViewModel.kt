package login.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import domain.model.AuthVerifyCodeModel
import domain.usecase.GetCodeUseCase
import login.presentation.viewmodel.model.LoginAction
import login.presentation.viewmodel.model.LoginEvent
import login.presentation.viewmodel.model.LoginState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.CommonConstants.LIMITS.Common.MAX_PHONE_CHARS
import utils.ext.isTextFieldFilled

class LoginViewModel : BaseViewModel<LoginState, LoginAction, LoginEvent>(
    initialState = LoginState()
), KoinComponent {
    private val getVerifyCode by inject<GetCodeUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is LoginEvent.PhoneChanged -> onPhoneChanged(viewEvent.phone)
            LoginEvent.OnAgreementClick -> onAgreementClick()
            LoginEvent.OnOfferClick -> onOfferClick()
            LoginEvent.OnPrivacyPolicyClick -> onPrivacyPolicyClick()
            LoginEvent.OnEntranceButtonCLick -> onEntranceButtonClick()
            LoginEvent.ResetAction -> onResetAction()
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
            isButtonEnabled = isButtonEnabled(phone = newPhone)
        )
    }

    private fun onOfferClick() {
        viewAction = LoginAction.OpenOffer
    }

    private fun onPrivacyPolicyClick() {
        viewAction = LoginAction.OpenPrivacyPolicy
    }

    private fun onEntranceButtonClick() {
        launchJob(
            context = appDispatchers.network,
            onError = {
                viewState = viewState.copy(isError = true)
            }
        ) {
            viewState = viewState.copy(isError = false)
            getVerifyCode(AuthVerifyCodeModel(viewState.phone))
            viewAction = LoginAction.OpenVerifyScreen
        }
    }

    private fun isButtonEnabled(
        isAgreementChecked: Boolean = viewState.isAgreementChecked,
        phone: String = viewState.phone
    ) = isAgreementChecked && phone.isTextFieldFilled(MAX_PHONE_CHARS)
}