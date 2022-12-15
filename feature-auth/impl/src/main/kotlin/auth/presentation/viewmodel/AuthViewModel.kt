package auth.presentation.viewmodel

import BaseViewModel
import auth.presentation.viewmodel.model.AuthAction
import auth.presentation.viewmodel.model.AuthEvent
import auth.presentation.viewmodel.model.AuthState
import domain.model.VerifyCodeModel
import domain.usecase.GetVerifyCodeUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthViewModel : BaseViewModel<AuthState, AuthAction, AuthEvent>(
    initialState = AuthState()
), KoinComponent {

    private val getVerifyCode by inject<GetVerifyCodeUseCase>()

    override fun obtainEvent(viewEvent: AuthEvent) {
        when (viewEvent) {
            is AuthEvent.OnAgreementClick -> {
                onAgreementClick()
            }
            is AuthEvent.OnOfferCLick -> {
                onOfferClick()
            }
            is AuthEvent.OnEntranceButtonCLick -> {
                onEntranceButtonClick()
            }
            is AuthEvent.PhoneChanged -> {
                onPhoneChanged(viewEvent.phone)
            }
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
        viewAction = AuthAction.OpenOffer
        resetAction()
    }

    private fun onEntranceButtonClick() {
        launchJob(onFinally = { viewState = viewState.copy(isLoading = false) }) {
            viewState = viewState.copy(isLoading = true)
            getVerifyCode(VerifyCodeModel(viewState.phone))
        }
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