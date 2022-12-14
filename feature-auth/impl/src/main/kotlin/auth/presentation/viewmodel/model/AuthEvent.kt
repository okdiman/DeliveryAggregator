package auth.presentation.viewmodel.model

sealed class AuthEvent {
    data class PhoneChanged(val phone: String) : AuthEvent()
    object OnAgreementClick : AuthEvent()
    object OnEntranceButtonCLick : AuthEvent()
    object OnOfferCLick : AuthEvent()
}