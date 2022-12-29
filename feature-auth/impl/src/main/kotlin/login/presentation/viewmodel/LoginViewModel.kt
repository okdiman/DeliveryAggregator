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
import utils.CommonConstants.LIMITS.Common.MAX_PHONE_CHARS
import utils.isTextFieldFilled

class LoginViewModel : BaseViewModel<LoginState, LoginAction, LoginEvent>(
    initialState = LoginState()
), KoinComponent {
    private val getVerifyCode by inject<GetCodeUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is LoginEvent.PhoneChanged -> onPhoneChanged(viewEvent.phone)
            LoginEvent.OnAgreementClick -> onAgreementClick()
            LoginEvent.OnOfferCLick -> onOfferClick()
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

    private fun onEntranceButtonClick() {
        launchJob(appDispatchers.network) {
            getVerifyCode(VerifyCodeModel(viewState.phone))
            viewAction = LoginAction.OpenVerifyScreen
        }
    }

    private fun isButtonEnabled(
        isAgreementChecked: Boolean = viewState.isAgreementChecked,
        phone: String = viewState.phone
    ) = isAgreementChecked && isTextFieldFilled(phone, MAX_PHONE_CHARS)
}