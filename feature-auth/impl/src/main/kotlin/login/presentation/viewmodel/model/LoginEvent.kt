package login.presentation.viewmodel.model

sealed interface LoginEvent {
    data class PhoneChanged(val phone: String) : LoginEvent
    object OnAgreementClick : LoginEvent
    object OnEntranceButtonCLick : LoginEvent
    object OnOfferClick : LoginEvent
    object OnPrivacyPolicyClick : LoginEvent
    object ResetAction : LoginEvent
}