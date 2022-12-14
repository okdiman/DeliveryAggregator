package auth.presentation.viewmodel

import BaseViewModel
import auth.presentation.viewmodel.model.AuthAction
import auth.presentation.viewmodel.model.AuthEvent
import auth.presentation.viewmodel.model.AuthState
import kotlinx.coroutines.delay

class AuthViewModel : BaseViewModel<AuthState, AuthAction, AuthEvent>(
    initialState = AuthState()
) {

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
        viewState = viewState.copy(isAgreementChecked = !viewState.isAgreementChecked)
    }

    private fun onPhoneChanged(newPhone: String) {
        viewState = viewState.copy(phone = newPhone)
    }

    private fun onOfferClick() {
        viewAction = AuthAction.OpenOffer
        launchJob {
            delay(500L)
            resetAction()
        }
    }

    private fun onEntranceButtonClick() {
        launchJob(onFinally = { viewState = viewState.copy(isLoading = false) }) {
            viewState = viewState.copy(isLoading = true)

        }
    }
}